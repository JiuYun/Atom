package com.origin.atom.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.origin.atom.dao.IUsersDaoMapper;
import com.origin.atom.model.ColumnModel;
import com.origin.atom.model.JM;
import com.origin.atom.model.SeracheFunModel;
import com.origin.atom.model.TableModel;
import com.origin.atom.server.ITableServer;

import com.origin.atom.server.impl.CoreServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
@Controller
public class HomeHandler {

    @Autowired
    private IUsersDaoMapper userDao;

    @Autowired
    private ITableServer tableServer;


    @RequestMapping("home")
    @ResponseBody
    public String home(){
        return "Hello";
    }


    @RequestMapping("saveUser")
    @ResponseBody
    public String saveUser(String id,String userName,String password,String email){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        params.put("userName",userName);
        params.put("password",id);
        params.put("email",email);
        userDao.saveUser(params);
        return "OK";
    }




    /****
     * 拿到所有表的信息
     * 
     * @return
     */
    @RequestMapping("tables")
    @ResponseBody
    public Object tables(HttpServletRequest requeset){
        Map<String,TableModel> tables = tableServer.tableModels("orgin");
        requeset.getSession().setAttribute("tables",tables);
    	return tables;
    }

    

    /***
     *
     * 根据指定表与列生成相应的功能
     * 
     * @param param
     * {
     *      "orderBy": ["article.create_time desc"],
     *      "columns": [
     *          "user.user_name",
     *          "user.password",
     *          "user.email",
     *          "article.title",
     *          "article.create_time",
     *          "admin.name"
     *      ],
     *      "funName": "articleList",
     *      "where": [
     *          "user.user_name",
     *          "article.create_time",
     *          "article.status:=1"
     *      ],
     *      "from":"article"
     * }
     * @return
     */
    public JM tableToFunction(String param,Map<String,TableModel> tables) throws IOException {
        JM jm = new JM();
        if(param != null){
            SeracheFunModel model = null;
            try {
                model = JSON.parseObject(param, SeracheFunModel.class);
            }catch (JSONException ex){
                jm.setMessage("JSON格式错误，无法解析的JSON的格式");return jm;
            }

            //1.对功能列进行检查
            String[] columns    = model.getColumns();
            String[] where      = model.getWhere();
            String[] orderBy    = model.getOrderBy();
            Map<String,List<String>> checkColumnName    =   new HashMap<String, List<String>>();
            try {
                analyticColumns(columns,checkColumnName);          //显示列
                analyticColumns(where,checkColumnName);            //条件列
                analyticColumns(orderBy,checkColumnName);          //排序列
            }catch (Exception ex){
                jm.setMessage(ex.getMessage());return jm;
            }
            if(checkColumnName.size() == 0){jm.setMessage("无效功能");return jm;}
            TableModel      table       = null;
            for(Map.Entry<String,List<String>> entry : checkColumnName.entrySet()){
                if((table = tables.get(entry.getKey())) == null){jm.setMessage(entry.getKey()+"数据表不存在");return jm;}{
                    for (String columnStr : entry.getValue()){
                        boolean exists = false;
                        for(ColumnModel columnModel : table.getColumn()){if(columnModel.getColumnName().equals(columnStr)){exists = true;}}
                        if(!exists){jm.setMessage(entry.getKey()+"."+columnStr+"：无法完成匹配");return jm;}
                    }
                }
            }
            CoreServer coreServer = new CoreServer();
            String resultXml = coreServer.serach(model);
            jm.setMessage(resultXml);
        }else{
            jm.setMessage("参数错误");
        }
        return jm;
    }



    public void analyticColumns(String[] columns,Map<String,List<String>> checkColumnName) throws Exception {
        String               column             =   null;
        String[]             columnInfo         =   null;
        if(columns != null)
            for(int i = 0; i < columns.length; i++){
                column       = columns[i];
                if(column == null || column.trim().length() < 3 ){continue;}
                columnInfo   = column.split("\\.");
                if(columnInfo.length != 2){throw  new Exception(column+"无法完成有效解析");}{
                    if(checkColumnName.get(columnInfo[0]) == null){
                        List<String> columnArray    =   new ArrayList<String>();
                        columnArray.add(columnInfo[1]);
                        checkColumnName.put(columnInfo[0],columnArray);
                    }else {
                        checkColumnName.get(columnInfo[0]).add(columnInfo[1]);
                    }
                }
            }
    }




    @RequestMapping("test")
    @ResponseBody
    public Object test(HttpServletRequest request) throws IOException {
        Map<String,TableModel> tables = (Map<String, TableModel>) request.getSession().getAttribute("tables");
        JM jm = this.tableToFunction("{\"orderBy\":[\"users.create_time\"],\"columns\":[\"users.id\",\"users.user_name\",\"users.password\",\"users.email\",\"users.create_time\"],\"funName\":\"userList\",\"where\":[\"users.user_name\"],\"comment\":\"测试查询解析器\",\"from\":\"users\"}",tables);
        return jm;
    }













//    public static void main(String[] args) {
////        HomeHandler homeHandler = new HomeHandler();
////        String result =  homeHandler.tableToFunction("{\"orderBy\":[\"article.create_time:desc\"],\"list\":[\"user.user_name\",\"user.password\",\"user.email\",\"article.title\",\"article.create_time\",\"admin.name\"],\"funName\": \"articleList\",\"where\": [\"user.user_name\",\"article.create_time\",\"article.status:=1\"],\"comment\":\"测试功能，作为测试生产代码使用。\"}");
////        System.out.println(result);
//
//        System.out.println("users.id".split("\\.")[0]+"users.id".split("\\.")[1]);
//
//    }






















}

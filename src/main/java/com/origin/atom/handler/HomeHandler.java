package com.origin.atom.handler;

import com.alibaba.fastjson.JSON;
import com.generator.me.FromatClassName;
import com.origin.atom.dao.IUsersDaoMapper;
import com.origin.atom.model.DataModel;
import com.origin.atom.model.TableModel;
import com.origin.atom.server.ITableServer;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 根据指定表与列生成相应的功能
     * 
     * @param param  [{"tableName":"user","columns":["user_Name","password","age"]},{"tableName":"aaaa","columns":["123","321"]}]
     * @return
     */
    public String tableToFunction(String param){
    	if(param != null){
    		List<DataModel> models = JSON.parseArray(param, DataModel.class);






    	}
    	return null;
    }


    @RequestMapping("createMyBatisXmlByTableName")
    @ResponseBody
    public Object crateMyBatisXmlByTableName(String tableName,HttpServletRequest request) throws IOException {
        Map<String,TableModel> models = (Map<String, TableModel>) request.getSession().getAttribute("tables");
        TableModel tableModel = models.get(tableName);

        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        gt.registerFunctionPackage("MeFunction",FromatClassName.class);
        Template t = gt.getTemplate("/bt/insert.bt");
        t.binding("columns",tableModel.getColumn());
        t.binding("tableName",tableModel.getName());
        return t.render();
    }




 
}

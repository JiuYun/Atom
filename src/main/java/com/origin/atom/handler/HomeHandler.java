package com.origin.atom.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.origin.atom.dao.IUsersDaoMapper;
import com.origin.atom.model.DataModel;
import com.origin.atom.server.ITableServer;

import org.beetl.ext.fn.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Object tables(){
    	return tableServer.tableModels("blog");
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
 
}

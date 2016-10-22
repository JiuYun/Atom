package com.origin.atom.handler;

import com.origin.atom.dao.IUsersDaoMapper;
import com.origin.atom.server.ITableServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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




    @RequestMapping("tables")
    @ResponseBody
    public Object tables(){
    	Map<String,Object> resultJson = new HashMap<String, Object>();
    	resultJson.put("params1", "params_1");
    	resultJson.put("params2", "params_2");
    	resultJson.put("params3", "params_3");
    	resultJson.put("params4", "params_4");
    	return resultJson;
    }















}

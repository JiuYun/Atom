package com.orgin.atom.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/9/10 0010.
 */
@Controller
public class HomeHandler {


    public HomeHandler() {
        System.out.println("我是HomeHandler 我被创建了");
    }
    @RequestMapping("home_{bb}")
    @ResponseBody
    public String home(@PathVariable("bb") String bb){
        return "Hello"+bb;
    }

}

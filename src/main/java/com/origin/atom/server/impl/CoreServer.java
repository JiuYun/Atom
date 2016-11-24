package com.origin.atom.server.impl;


import com.alibaba.fastjson.JSON;
import com.generator.me.FromatClassName;
import com.origin.atom.FileUtil.FileUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

/***
 *
 * 核心
 *
 */
public class CoreServer {

    private ClasspathResourceLoader resourceLoader  = null;
    private Configuration           cfg             = null;
    private GroupTemplate           gt              = null;

    public CoreServer() throws IOException {
        resourceLoader  = new ClasspathResourceLoader();
        cfg             = Configuration.defaultConfiguration();
        gt              = new GroupTemplate(resourceLoader, cfg);
        gt.registerFunctionPackage("MeFunction",FromatClassName.class);
    }


    /****
     *
     * 创建insertSQL语句
     *
     * @param params
     * @return
     */
    public String insert(String params){
        HashMap temp = JSON.parseObject(params,HashMap.class);
        Template t = gt.getTemplate("/bt/insert.bt");
        t.binding("columns",temp.get("columns"));
        t.binding("tableName",temp.get("tableName"));
        return t.render();
    }


    /***
     *
     * 创建updateSql语句
     *
     * @param params
     * @return
     */
    public String update(String params){
        HashMap temp = JSON.parseObject(params,HashMap.class);
        Template t = gt.getTemplate("/bt/update.bt");
        t.binding("columns",temp.get("columns"));
        t.binding("tableName",temp.get("tableName"));
        return t.render();
    }


    /****
     *
     * 创建删除语句
     *
     * @param params
     * @return
     */
    public String delete(String params){
        HashMap temp = JSON.parseObject(params,HashMap.class);
        Template t = gt.getTemplate("/bt/delete.bt");
        t.binding("columns",temp.get("columns"));
        t.binding("tableName",temp.get("tableName"));
        return t.render();
    }


    /***
     *
     * 查询
     *
     *
     * @param params
     * @return
     */
    public String serach(String params){
        HashMap temp = JSON.parseObject(params,HashMap.class);
        Template t = gt.getTemplate("/bt/serach.bt");
        t.binding("columns",temp.get("columns"));
        t.binding("tableName",temp.get("tableName"));
        return t.render();
    }




    public static void main(String[] args) throws IOException {
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\origin\\atom\\mapping";

        CoreServer coreServer = new CoreServer();
        String str = "{\"tableName\":\"hello\",\"columns\":[{\"columnName\":\"id\",\"isWhere\":true},{\"columnName\":\"user_Name\",\"isWhere\":true},{\"columnName\":\"password\",\"isWhere\":false},{\"columnName\":\"pro_Code\",\"isWhere\":false}]}";
        String insert = coreServer.delete(str);
        FileUtil.saveToFile(projectPath,"hello.xml",insert,true);
        System.out.println(insert);















    }

}

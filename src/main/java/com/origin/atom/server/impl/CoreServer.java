package com.origin.atom.server.impl;


import com.alibaba.fastjson.JSON;
import com.generator.me.FromatClassName;
import com.origin.atom.FileUtil.FileUtil;
import com.origin.atom.model.SeracheFunModel;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
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
     * @param fun
     * @return
     */
    public String serach(SeracheFunModel fun){
        Template t = gt.getTemplate("/bt/serach.bt");
        t.binding("fun",fun);
        return t.render();
    }



    /***
     *
     * 将文件字符串保存到XML中
     *
     * @param path                          文件路径
     * @param fileName                      文件名称
     * @param appendContext                 文件内容
     * @return
     */
    public boolean saveToXml(String path,String fileName,String appendContext){
        try {
            if(FileUtil.existsFile(path,fileName)){
                String fileContext = FileUtil.readFile(path,fileName);
                int lastMapperIndex = fileContext.lastIndexOf("</mapper>");
                String contextQ1 = fileContext.substring(0,lastMapperIndex);
                String contextQ2 = fileContext.substring(lastMapperIndex);
                String contextQ3 = contextQ1 + "\r\n"+ appendContext + "\r\n" + contextQ2;
                FileUtil.saveToFile(path,fileName,contextQ3,false);
            }else {
                String mapperQ1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<!DOCTYPE mapper SYSTEM \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                        "<mapper namespace=\"com.origin.atom.dao.ITableDaoMapper\">";
                String mapperQ2 = "</mapper>";
                String mapperQ3 = mapperQ1 + "\r\n"+ appendContext + "\r\n" + mapperQ2;
                FileUtil.saveToFile(path,fileName,mapperQ3,false);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        String projectPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\origin\\atom\\mapping";

        String context = "\t<!-- 测试查询解析器 -->\r" +
                "\t<select id=\"userList\" parameterType=\"Map\" resultType=\"\">\r" +
                "\t    select users.id,users.user_name,users.password,users.email,users.create_time from users\r" +
                "\t    <where>\n" +
                "\t        users.user_name = #{users.userName}\n" +
                "\t    </where>\n" +
                "\t    order by users.create_time\n" +
                "\t</select>";
        CoreServer coreServer = new CoreServer();
        coreServer.saveToXml(projectPath,"Hello.xml",context);

    }
}

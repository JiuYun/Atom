package com.atom.codegen;


import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.core.Ioc;
import com.atom.codegen.db.LoadTableInfo;
import com.atom.codegen.model.TableModel;
import com.generator.me.FormatClassName;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.util.Map;

/***
 *
 * 代码生成器入口
 *
 */
public class Entrance {

    /***
     * 代码生成器入口
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // 1.读取配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig(){{
            setDriver("com.mysql.jdbc.Driver");
            setUrl("jdbc:mysql://localhost:3306/blog");
            setUserName("root");
            setPassword("root");
            setSchema("blog");
            setName("localhost");
            setDataType("mysql");
        }};
        Ioc.addBean("dataSourceConfig",dataSourceConfig);

        // 2.转换数据库表到内存中
        LoadTableInfo loadTableInfo = new LoadTableInfo();
        loadTableInfo.loadDataTableInfo();

        // 3.读取模板文件、使用模板
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        gt.registerFunctionPackage("MeFunction",FormatClassName.class);
        Template t = gt.getTemplate("/base/tpl/updateByPrimaryKeySelective.bt");

        Map<String,TableModel> tableMap = (Map<String, TableModel>) Ioc.getBean("tableMap");
        for (String key : tableMap.keySet()) {
            TableModel model = tableMap.get(key);
            t.binding("table",model);
            t.binding("className",model.getName());
            t.binding("columns",model.getColumn());
            t.binding("packageInfo","com.qianyun.core");
            String str = t.render();
            System.out.println(str);
        }


    }



}

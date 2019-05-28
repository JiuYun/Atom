package com.atom.codegen;


import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.core.Ioc;
import com.atom.codegen.db.LoadTableInfo;

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

    }



}

package com.qiandu.Generator.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import com.generator.me.FromatClassName;
import com.qiandu.Generator.Model.ColumnModel;
import com.qiandu.Generator.Model.TableModel;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

/***
 * 生成项目所需的Java类
 * @author Administrator
 *
 */
public class Entrance {


    private static String url="jdbc:mysql://localhost:3306/orgin";

    private static String username="root";

    private static String password = "root";


    public void start() throws Exception {

        //获取数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        //创建Statement
        Statement statement = connection.createStatement();
        //ResultSet
        //show tables
        Map<String,TableModel> tableMap = new HashMap<String,TableModel>();//装表对象
        ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME,COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,COLUMN_KEY FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'orgin'");
        TableModel tableModel = null;
        ColumnModel columnModel = null;
        while (resultSet.next()) {
            String key = resultSet.getString(1);
            if(tableMap.get(key) == null){//当前列所对应的表在集合中已经存在了
                //创建对应的资源
                tableModel = new TableModel();
                tableModel.setName(resultSet.getString(1));
                tableModel.setColumn(new HashSet<ColumnModel>());
                tableMap.put(key,tableModel);
            }
            columnModel = new ColumnModel();
            columnModel.setColumnName(resultSet.getString(2));
            columnModel.setDataType(resultSet.getString(3));
            columnModel.setNotes(resultSet.getString(4));
            //解析关系
            String col = resultSet.getString(4);
            String[] result = col.split("FK");
            if (result.length == 2) {
                String[] fk = result[1].split(" ");
                String type1 = fk[1].substring(0,1);
                String type2 = fk[1].substring(1);
                String type = type1.toUpperCase()+type2;
                columnModel.setDataType(type);
                columnModel.setColumnName(fk[1]);
            }
            if(resultSet.getString(5) == "PRI") {columnModel.setPk(true);}
            tableModel.getColumn().add(columnModel);
        }
        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
        //BeanUtil.doPojo(tableMap);

        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        gt.registerFunctionPackage("MeFunction",FromatClassName.class);
        Template t = gt.getTemplate("/JavaPojo.bt");

        for (String key : tableMap.keySet()) {
            tableMap.get(key);
            t.binding("className",tableModel.getName());
            t.binding("columns",tableModel.getColumn());
            String str = t.render();
            System.out.println(str);
        }
    }

}

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

import com.qiandu.Generator.Model.ColumnModel;
import com.qiandu.Generator.Model.TableModel;

/***
 * 生成项目所需的Java类
 * @author Administrator
 *
 */
public class Entrance {


    private static String url="jdbc:mysql://localhost:3306/test";

    private static String username="root";

    private static String password = "0708";


    public void start() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //与用户进行交互
        Scanner scanner = new Scanner(System.in);
        System.out.println("["+dateFormat.format(new Date())+"]\t项目环境检测正常！");
        String projecturl = System.getProperty("user.dir");
        System.out.print("请输入生成的包名:");
        String packagename = scanner.nextLine();

        //获取数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        //创建Statement
        Statement statement = connection.createStatement();
        //ResultSet
        //show tables
        Map<String,TableModel> tableMap = new HashMap<String,TableModel>();//装表对象
        ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME,COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,COLUMN_KEY FROM  INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'test'");
        TableModel tableModel = null;
        ColumnModel columnModel;
        while (resultSet.next()) {
            String key = resultSet.getString(1);
            if(tableMap.get(key) != null){//说明这个表的数据还没完
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

                if(resultSet.getString(5) == "PRI")
                    columnModel.setPk(true);
                tableMap.get(key).getColumn().add(columnModel);
            }else{//说明这个是个新表
                tableModel = new TableModel();
                columnModel = new ColumnModel();
                tableModel.setName(resultSet.getString(1));
                tableModel.setColumn(new HashSet<ColumnModel>());
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

                if(resultSet.getString(5) == "PRI")
                    columnModel.setPk(true);
                tableModel.getColumn().add(columnModel);
                tableMap.put(key,tableModel);
            }
        }
        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
        BeanUtil.doPojo(tableMap);
    }

}

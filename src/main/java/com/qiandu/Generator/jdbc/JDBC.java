package com.qiandu.Generator.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.qiandu.Generator.Model.ColumnModel;
import com.qiandu.Generator.Model.TableModel;
import com.qiandu.Generator.core.Ioc;


/****
 * 获取数据库连接
 * @author Administrator
 *
 */
public class JDBC {

    /**
     * 获得连接
     * @return
     * @throws Exception
     */
    public static Connection getConn() throws Exception{
        return DriverManager.getConnection(Ioc.getBean("dburl").toString(),Ioc.getBean("dbuser").toString(),Ioc.getBean("dbpasswrd").toString());
    }

    /**
     * 释放资源
     * @param rs
     * @param stmt
     * @param conn
     */
    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs!=null)
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if(stmt!=null)
            try {
                stmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        if(conn !=null)
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }


    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        Map<String,String> parmas = new HashMap<String, String>();
        parmas.put("name","zhangs");
        parmas.put("name2","zhangs2");
        parmas.put("name3","zhangs3");
        parmas.put("name4","zhangs4");
        Ioc.addBean("dburl", "jdbc:mysql://192.168.0.40:3306/test");
        Ioc.addBean("dbuser", "root");
        Ioc.addBean("dbpasswrd", "root");
        try {
            jdbc.gatTableModel(parmas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /****
     * 获取数据库全部表信息
     *
     * @return
     * @throws Exception
     */
    public Map<String,TableModel> gatTableModel(Map<String,String> params) throws Exception{
        //解析参数配置Sql
        String paramssql = "";
        for (String key : params.keySet()) {
            paramssql += " and "+key+" = "+params.get(key)+"," ;
        }
        paramssql = paramssql.substring(0,paramssql.lastIndexOf(","));
        System.out.println(paramssql);


        //获取数据库连接
        Connection connection = getConn();
        //创建Statement
        Statement statement = connection.createStatement();
        Map<String,TableModel> tableMap = new HashMap<String,TableModel>();//装表对象
        ResultSet resultSet = statement.executeQuery("SELECT TABLE_NAME,COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT,COLUMN_KEY FROM  INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = test");
        TableModel tableModel = null;
        ColumnModel columnModel;
        while (resultSet.next()) {
            String key = resultSet.getString(1);
            if(tableMap.get(key) != null){//说明这个表的数据还没完
                columnModel = new ColumnModel();
                columnModel.setColumnName(resultSet.getString(2));
                columnModel.setDataType(resultSet.getString(3));
                columnModel.setNotes(resultSet.getString(4));
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
                if(resultSet.getString(5) == "PRI")
                    columnModel.setPk(true);
                tableModel.getColumn().add(columnModel);
                tableMap.put(key,tableModel);
            }
        }
        //关闭连接
        close(resultSet, statement, connection);

        return tableMap;
    }



















}

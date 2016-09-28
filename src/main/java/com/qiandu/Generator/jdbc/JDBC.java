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
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {try {rs.close();} catch (SQLException e) {e.printStackTrace();}}
        if (stmt != null) {try {stmt.close();} catch (SQLException e) {e.printStackTrace();}}
        if (conn != null) {try {conn.close();} catch (SQLException e) {e.printStackTrace();}}
    }



















}

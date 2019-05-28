package com.atom.codegen.db;

import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.core.Ioc;

import java.sql.*;


/****
 * 获取数据库连接
 * @author Administrator
 *
 */
public class JDBCConnection {

    /**
     * 获得连接
     * @return
     * @throws Exception
     */
    public static Connection getConn() throws Exception{
        DataSourceConfig dataSourceConfig = (DataSourceConfig) Ioc.getBean("dataSourceConfig");
        return DriverManager.getConnection(dataSourceConfig.getUrl(),dataSourceConfig.getUserName(),dataSourceConfig.getPassword());
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

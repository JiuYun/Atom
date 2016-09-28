package com.qiandu.Generator.command;

import java.sql.*;
import java.util.Scanner;

/**
 * Created by Martion on 2016/9/16 0016.
 * 对Mysql查询的一些包装
 *
 *
 */
public class SqlCommands {
    private static String url="jdbc:mysql://localhost:3306/orgin";
    private static String username="root";
    private static String password = "root";

    public static void main(String[] args){
        System.out.println("欢迎使用程序助手------------------------------------>");
        System.out.println(":exit退出程序");
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        while (!exit){
            String command = in.nextLine();
            if(command.trim().equals(":exit")){
                exit = true;
                System.out.println("感谢使用！");
                break;
            }
            try {
                exSql(command);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    /***
     * 执行SQL
     * @param sql
     */
    public static void exSql(String sql) throws SQLException {
//获取数据库连接
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int resultColumentCount = resultSet.getMetaData().getColumnCount();
        for (int i = 1; i <= resultColumentCount;i++){System.out.print(resultSet.getMetaData().getColumnName(i)+"\t\t");}
        System.out.println();
        while (resultSet.next()) {
            for(int i = 1;i <= resultColumentCount; i++){
                System.out.print(resultSet.getString(i)+"\t");
            }
            System.out.println();
        }
    }
















}

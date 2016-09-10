package com.qiandu.Generator.constant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/***
 * 系统常量类包装类
 * @author Administrator
 *
 */
public final class Constant {

    //注释部分
    public static final String ANNOTATION_AUTHOR="@author";
    public static final String ANNOTATION_NewDate="@NewDate";
    public static final String ANNOTATION_EXPLAIN="@Explain";

    //实体包名部分
    public static final String DEFAULT_DAO_PACKAGE="com.qiandu.dao";
    public static final String DEFAULT_MODEL_PACKAGE="com.qiandu.pojo";
    public static final String DEFAULT_SERVER_PACKAGE="com.qiandu.server";

    /***
     * 数据库数据类型到Java数据类型
     */
    private static final Map<String,String> type = new HashMap<String,String>();

    /***
     * 需要导的包
     */
    private static final Map<String,String> bao = new HashMap<String,String>();


    /***
     * 数据库驱动
     */
    public static final Set<String> dirver = new HashSet<String>();








    /***
     * 获取数据库中字段类型对应的Java类型</br>
     *
     * @param DataType 数据库中的类型
     * @return java中对应的类型
     */
    public static String DataTypeToJavaType(String DataType){
        return type.get(DataType);
    };

    /***
     * 获取数据类型需要对应的包
     * @param dataType
     * @return java数据类型对应的包地址
     */
    public static String getPackage(String dataType){
        return bao.get(dataType);
    }


    /***
     * 初始化基本数据
     */
    static{
        //数据填充数据库与程序数据类型映射
        type.put("varchar","String");
        type.put("char","char");
        type.put("blob", "byte[]");
        type.put("text", "String");
        type.put("integer", "int");
        type.put("int","int");
        type.put("bigint","int");
        type.put("decimal","Timestamp");
        type.put("double","double");
        type.put("double precision	float", "double");
        type.put("enum", "String");
        type.put("float","float");
        type.put("bit","type[]");
        type.put("longblob","byte[]");
        type.put("longtext","String");
        type.put("mediumblob","byte[]");
        type.put("mediumint", "int");
        type.put("numeric", "long");
        type.put("datatime","Date");
        type.put("datetime","Date");
        type.put("date","Date");
        type.put("double","Double");
        type.put("tinyint","int");

        //添加程序数据类型对应的包，当然这里我们目前做生成Pojo需要不了多少对应包
        bao.put("date","java.util.Date;");
        bao.put("datetime","java.util.Date;");
        bao.put("set","java.util.Set;");
        bao.put("hashset","java.util.HashSet;");
        bao.put("util","java.util.*;");


        //注册驱动
        dirver.add("com.mysql.jdbc.Driver");
        dirver.add("com.sybase.jdbc3.jdbc.SybDriver");
        dirver.add("oracle.jdbc.driver.OracleDriver");
        dirver.add("com.microsoft.jdbc.sqlserver.SQLServerDriver");

    }




}

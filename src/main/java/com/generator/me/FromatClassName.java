package com.generator.me;

import org.beetl.core.Context;
import org.beetl.core.Function;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class FromatClassName{


    /***
     * 传入数据库表明转换对应的JavaClass名称
     * @param tableName
     * @return
     */
    public String fromatClassName(String tableName){
        if(tableName != null && tableName.trim().length() == 0){return "";}
        if(tableName.indexOf("_") != -1){
            String[] array = tableName.split("_");
            String returnStr = "";
            for(String str : array){if(str.trim().length() > 0){returnStr += str.substring(0,1).toUpperCase()+str.substring(1,str.length());}}
            return returnStr;
        }else{
            return tableName.substring(0,1).toUpperCase()+tableName.substring(1,tableName.length());
        }
    }


    /***
     * 传入数据库表列明转换成Java对应的列明
     * @param columnName
     * @return
     */
    public String fromatColumnName(String columnName){
        if(columnName != null && columnName.trim().length() == 0){return "";}
        if(columnName.indexOf("_") != -1){
            String[] array = columnName.split("_");
            String returnStr = "";
            for(int i = 0; i< array.length; i++){
                if(array[i].trim().length() > 0){
                    if(i == 0){
                        returnStr += array[i].substring(0,1).toLowerCase()+array[i].substring(1,array[i].length());
                    }else{
                        returnStr += array[i].substring(0,1).toUpperCase()+array[i].substring(1,array[i].length());
                    }
                }
            }
            return returnStr;
        }else{
            return columnName.substring(0,1).toLowerCase()+columnName.substring(1,columnName.length());
        }
    }




}

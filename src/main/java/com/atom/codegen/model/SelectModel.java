package com.atom.codegen.model;

import com.atom.codegen.core.Ioc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 查询SQL模型
 */
public class SelectModel {

    List<SelectColumns> columnsList     = new ArrayList<>();
    String              formatContent   = null;
    List<WhereColumns>  whereColumns    = new ArrayList<>();
    String              functionName;
    Map<String,String>  selectTables    = new HashMap<>();

    public String getFormatContent() {
        return formatContent;
    }

    public void setFormatContent(String formatContent) {
        this.formatContent = formatContent;
    }

    public List<SelectColumns> getColumnsList() {
        return columnsList;
    }

    public List<WhereColumns> getWhereColumns() {
        return whereColumns;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Map<String, String> getSelectTables() {
        return selectTables;
    }

    public static class SelectColumns{
        private String columnsName;
        private String tableName;
        private String alias;
        private String prefix;
        private SelectModel parent;


        public String getColumnsName() {
            return columnsName;
        }

        public void setColumnsName(String columnsName) {
            this.columnsName = columnsName;
        }

        public String getTableName() {
            return tableName;
        }

        public void setTableName(String tableName) {
            this.tableName = tableName;
        }

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public SelectModel getParent() {
            return parent;
        }

        public void setParent(SelectModel parent) {
            this.parent = parent;
        }
    }

    public static class WhereColumns{
        private String expression;
        private String oprator;
        private SelectModel parent;

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getOprator() {
            return oprator;
        }

        public void setOprator(String oprator) {
            this.oprator = oprator;
        }

        public SelectModel getParent() {
            return parent;
        }

        public void setParent(SelectModel parent) {
            this.parent = parent;
        }

        /***
         * 将自身转换成Mybatis查询条件,写的什么垃圾玩意啊？？？？？？？？？？？？？
         *
         * @return
         */
        public String whereCondition(){
            String[] condition = expression.split(".");
            if(condition.length == 1){
                // 在所有表中查询当前列
                for(String tableName : parent.getSelectTables().keySet()){

                }
            }else{
                String tableName = parent.getSelectTables().get(condition[0]);

                // 查询当前列是否是在查询的列中有别名
                for(SelectColumns columnModel : parent.getColumnsList()){
                    if(columnModel.getColumnsName().equalsIgnoreCase(condition[1])){
                        return columnModel.getAlias();
                    }
                }


            }
            return null;
        }
    }



}

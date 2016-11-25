package com.origin.atom.model;


/*****
 *
 * 查询功能封装实体
 *
 */
public class SeracheFunModel {

    private String[] orderBy;                   //按照什么字段排序

    private String[] columns;                   //查询的列数

    private String funName;                     //功能名称

    private String[] where;                     //查询条件

    private String comment;                     //功能描述

    private String from;                        //谁是主表



    public String[] getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String[] orderBy) {
        this.orderBy = orderBy;
    }

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String[] getWhere() {
        return where;
    }

    public void setWhere(String[] where) {
        this.where = where;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
}

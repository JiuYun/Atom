package com.qiandu.Generator.Model;

import java.util.Set;


/****
 * 数据库表模型
 */
public class TableModel {

    private String name;                    //  表名

    private Set<ColumnModel> column;        //  列的集合

    private String  notes;                  //  描述

    public TableModel() {
    }

    public TableModel(String name, Set<ColumnModel> column, String notes) {
        this.name = name;
        this.column = column;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ColumnModel> getColumn() {
        return column;
    }

    public void setColumn(Set<ColumnModel> column) {
        this.column = column;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "TableModel [name=" + name + ", tcolumn=" + column + "]\r";
    }




}

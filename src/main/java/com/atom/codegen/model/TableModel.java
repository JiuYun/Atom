package com.atom.codegen.model;

import java.util.List;


public class TableModel {

    private String name;                                        //  表名
    private List<ColumnModel> column;                           //  列的集合
    private List<ColumnModel> pk;                               //  主键
    private String notes;                                       //  描述

    public TableModel() {
    }

    public TableModel(String name, List<ColumnModel> column, List<ColumnModel> pk, String notes) {
        this.name = name;
        this.column = column;
        this.pk = pk;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ColumnModel> getColumn() {
        return column;
    }

    public void setColumn(List<ColumnModel> column) {
        this.column = column;
    }

    public List<ColumnModel> getPk() {
        return pk;
    }

    public void setPk(List<ColumnModel> pk) {
        this.pk = pk;
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

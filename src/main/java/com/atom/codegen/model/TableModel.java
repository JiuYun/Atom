package com.atom.codegen.model;

import java.util.Set;


public class TableModel {

    private String name;                                    //  表名

    private Set<ColumnModel> column;                        //  列的集合

    private Set<ColumnModel> pk;                            //  主键

    private String notes;                                   //  描述

    public TableModel() {
    }

    public TableModel(String name, Set<ColumnModel> column,Set<ColumnModel> pk, String notes) {
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

    public Set<ColumnModel> getPk() {
        return pk;
    }

    public void setPk(Set<ColumnModel> pk) {
        this.pk = pk;
    }

    @Override
    public String toString() {
        return "TableModel [name=" + name + ", tcolumn=" + column + "]\r";
    }




}

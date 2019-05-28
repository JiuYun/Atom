package com.atom.codegen.model;

import java.util.Set;


public class TableModel {

    private String name;                                    //  表名

    private Set<ColumnModel> column;                        //  列的集合

    private String notes;                                   // 描述

    public TableModel() {
    }

    public TableModel(String name, Set<ColumnModel> column) {
        this.name = name;
        this.column = column;
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

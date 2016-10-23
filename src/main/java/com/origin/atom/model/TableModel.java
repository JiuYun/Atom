package com.origin.atom.model;

import java.util.Set;


/****
 * 数据库表模型
 * @author Chuang
 *	不为别的只为以后的开发方便
 */
public class TableModel {

    private String name;//表名

    private Set<ColumnModel> column;//列的集合

    public TableModel() {
        // TODO Auto-generated constructor stub
    }

    public TableModel(String name, Set<ColumnModel> column) {
        super();
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

    @Override
    public String toString() {
        return "TableModel [name=" + name + ", tcolumn=" + column + "]\r";
    }




}

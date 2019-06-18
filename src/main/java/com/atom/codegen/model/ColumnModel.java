package com.atom.codegen.model;

import java.io.Serializable;



public class ColumnModel implements Serializable{

    private static final long serialVersionUID = 1817142740849358516L;

    private String columnName;                  // 列名
    private String dataType;                    // 数据类型
    private boolean isnull;                     // 是否能为空
    private boolean pk;                         // 是否是主键
    private String notes;                       // 注释说明


    public ColumnModel() {

    }


    public ColumnModel(String columnName, String dataType, boolean isnull, boolean pk, String notes) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.isnull = isnull;
        this.pk = pk;
        this.notes = notes;
    }

    public String getColumnName() {
        return columnName;
    }


    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }


    public String getDataType() {
        return dataType;
    }


    public void setDataType(String dataType) {
        this.dataType = dataType;
    }


    public boolean isIsnull() {
        return isnull;
    }


    public void setIsnull(boolean isnull) {
        this.isnull = isnull;
    }


    public boolean isPk() {
        return pk;
    }

    public void setPk(boolean pk) {
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
        return "ColumnModel [columnName=" + columnName + ", \tdataType="
                + dataType + ", \tisnull=" + isnull + ", \tpk=" + pk
                + ", \tnotes=" + notes + "]\r";
    }





}

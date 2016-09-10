package com.qiandu.Generator.Model;

import java.io.Serializable;



/****
 * 数据库列的模型
 * @author Chuang
 *	2015 4 18
 *	不为别的只为以后的开发方便
 */
public class ColumnModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1817142740849358516L;

    private String columnName;//列名

    private String dataType;//数据类型

    private boolean isnull;//是否能为空

    private boolean isPk;//是否是主键

    private String notes;//注释说明


    public ColumnModel() {
        // TODO Auto-generated constructor stub
    }


    public ColumnModel(String columnName, String dataType, boolean isnull,
                       boolean isPk, String notes) {
        super();
        this.columnName = columnName;
        this.dataType = dataType;
        this.isnull = isnull;
        this.isPk = isPk;
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
        return isPk;
    }


    public void setPk(boolean isPk) {
        this.isPk = isPk;
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
                + dataType + ", \tisnull=" + isnull + ", \tisPk=" + isPk
                + ", \tnotes=" + notes + "]\r";
    }





}

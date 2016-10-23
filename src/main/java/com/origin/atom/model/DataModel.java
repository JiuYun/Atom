package com.origin.atom.model;


/***
 * 
 * @author chuang
 *
 */
public class DataModel {
	
	private String 		tableName;
	private String[] 	columns;			//列名
	
	
	
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String[] getColumns() {
		return columns;
	}
	public void setColumns(String[] columns) {
		this.columns = columns;
	}
	

}

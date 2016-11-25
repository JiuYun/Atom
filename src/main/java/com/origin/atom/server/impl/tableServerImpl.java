package com.origin.atom.server.impl;

import com.generator.me.FromatClassName;
import com.origin.atom.dao.ITableDaoMapper;
import com.origin.atom.model.ColumnModel;
import com.origin.atom.model.TableModel;
import com.origin.atom.server.ITableServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
@Service
public class tableServerImpl implements ITableServer{

    @Autowired
    private ITableDaoMapper tableDaoMapper;


	public Map<String,TableModel> tableModels(String dataBaseName) {
		FromatClassName				fc				= new FromatClassName();
    	List<Map<String, String>> 	columns 		= tableDaoMapper.tables(dataBaseName);
    	Map<String,TableModel> 		tables 			= null;
    	if(columns != null){
    		tables 									= new HashMap<String, TableModel>();
    		TableModel 				tableModel 		= null;
    		Map<String, String> 	column 			= null;
    		ColumnModel				columnModel 	= null;
			for (int i = 0; i < columns.size(); i++) {
				column = columns.get(i);
				if(column == null){continue;}
				if(tables.get( column.get("table_name") ) == null){
	                //创建对应的资源
	                tableModel = new TableModel();
	                tableModel.setColumn(new HashSet<ColumnModel>());
	                tableModel.setName( column.get("table_name") );
	                tables.put( column.get("table_name"),tableModel );
			    }
	            columnModel = new ColumnModel();
	            columnModel.setColumnName( 	column.get("column_name") );
	            columnModel.setDataType( column.get("data_type") );
				if("PRI".equals(column.get("column_key"))){columnModel.setPk(true);}else {columnModel.setPk(false);}
				columnModel.setNotes( column.get("column_comment") );

				//解析关系
				if(columnModel.getNotes() != null && columnModel.getNotes().trim().length() >= 6){
					int startIndex,endIndex;
					startIndex 	= columnModel.getNotes().indexOf("[");
					endIndex	= columnModel.getNotes().indexOf("]");
					if(startIndex >= 0 && endIndex > (startIndex+4)){
						String 		FKStr 		= columnModel.getNotes().substring(startIndex+1,endIndex);
						String[] 	optArray	= FKStr.split(",");
						for (String t : optArray){
							String[] opt = t.split(":");
							if(opt.length == 2){
								if(opt[0].equals("FK")){
									columnModel.setDataType(fc.fromatClassName(opt[1]));
									columnModel.setColumnName(opt[1]);
								}
							}
						}
					}
				}
	            tableModel.getColumn().add(columnModel);
			}
    	}
        return tables != null ? tables : null;
    }


}

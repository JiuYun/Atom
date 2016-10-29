package com.origin.atom.server.impl;

import com.origin.atom.dao.ITableDaoMapper;
import com.origin.atom.model.ColumnModel;
import com.origin.atom.model.TableModel;
import com.origin.atom.server.ITableServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
	            columnModel.setNotes( column.get("column_comment") );
	            //解析关系
	            String col = column.get("column_key");
	            String[] result = col.split("FK");
	            if (result.length == 2) {
	                String[] fk = result[1].split(" ");
	                String type1 = fk[1].substring(0,1);
	                String type2 = fk[1].substring(1);
	                String type = type1.toUpperCase()+type2;
	                columnModel.setDataType(type);
	                columnModel.setColumnName(fk[1]);
	            }
	            if(column.get("column_key").toString() == "PRI") {columnModel.setPk(true);}
	            tableModel.getColumn().add(columnModel);
			}
    	}
        return tables != null ? tables : null;
    }
    
    
}

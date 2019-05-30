package com.atom.codegen.db;

import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.core.Ioc;
import com.atom.codegen.model.ColumnModel;
import com.atom.codegen.model.TableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/***
 * 加载数据库信息到内存中
 */
public class LoadTableInfo {


    public void loadDataTableInfo() throws Exception {
        DataSourceConfig    dataSourceConfig    = (DataSourceConfig) Ioc.getBean("dataSourceConfig");
        Connection          connection          = JDBCConnection.getConn();
        Statement           statement           = connection.createStatement();
        Map<String,TableModel> tableMap         = new HashMap<String,TableModel>();             // 表信息

        // 获取数据库表信息
        String      showTableTpl    = "select table_schema,table_name,table_comment from information_schema.`tables` where table_schema = '%s'";
        ResultSet   tableResultSet  = statement.executeQuery(String.format(showTableTpl,dataSourceConfig.getSchema()));
        while (tableResultSet.next()){
            tableMap.put(tableResultSet.getString(2) ,
                    new TableModel(tableResultSet.getString(2),new HashSet<ColumnModel>(),
                            new HashSet<ColumnModel>(),
                            tableResultSet.getString(3)));
        }

        // 获取数据库表信息
        String showColumnTpl    = "select table_name,column_name,data_type,column_comment,column_key from information_schema.columns where table_schema = '%s'";
        ResultSet   result      = statement.executeQuery(String.format(showColumnTpl,dataSourceConfig.getSchema()));
        ColumnModel columnModel = null;
        while (result.next()){
            // 解析列
            columnModel = new ColumnModel(
                    result.getString(2),
                    result.getString(3),
                    false,
                    result.getString(5).equals("PRI") ? true : false,
                    result.getString(4)
            );
            tableMap.get(result.getString(1)).getColumn().add(columnModel);
            if(columnModel.isPk()){
                tableMap.get(result.getString(1)).getPk().add(columnModel);     // 主键
            }
        }
        Ioc.addBean("tableMap",tableMap);
        JDBCConnection.close(result,statement,connection);
    }




}

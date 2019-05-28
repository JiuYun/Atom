package com.atom.codegen.db;

import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.core.Ioc;
import com.qiandu.Generator.Model.ColumnModel;
import com.qiandu.Generator.Model.TableModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/***
 * 加载数据库信息到内存中
 */
public class LoadTableInfo {


    public void loadDataTableInfo() throws Exception {
        DataSourceConfig dataSourceConfig = (DataSourceConfig) Ioc.getBean("dataSourceConfig");
        Connection connection = JDBCConnection.getConn();
        Statement statement = connection.createStatement();

        // 获取数据库表信息
        Map<String,TableModel> tableMap = new HashMap<String,TableModel>();
        ResultSet   result      = statement.executeQuery("select table_name,column_name,data_type,column_comment,column_key from information_schema.columns where table_schema = '" + dataSourceConfig.getSchema() + "'");
        TableModel  tableModel  = null;
        ColumnModel columnModel = null;
        while (result.next()){
            String tableName = result.getString(1);
            if(tableMap.containsKey(tableName)){

            }

            System.out.println(String.format("表:%s,列:%s,类型:%s,注释:%s,键类型:%s",
                    result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            ));
        }





    }




}

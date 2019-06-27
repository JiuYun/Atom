package com.atom.codegen;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.util.JdbcConstants;
import com.atom.codegen.model.SelectModel;

import java.util.List;
import java.util.Map;

public class SqlUtils {


    public static SelectModel selectSqlToSelectModel(String sql){
        SelectModel         selectModel = new SelectModel();
        String              dbType      = JdbcConstants.MYSQL;
        List<SQLStatement>  stmtList    = SQLUtils.parseStatements(sql, dbType);

        for (int i = 0; i < stmtList.size(); i++) {
            SQLStatement stmt = stmtList.get(i);
            ExportTableAliasVisitor exportTableAliasVisitor = new ExportTableAliasVisitor();
            stmt.accept(exportTableAliasVisitor);

            // 解析查询相关表
            Map<String, SQLTableSource> aliasMap = exportTableAliasVisitor.getAliasMap();
            for(String key : aliasMap.keySet()){
                selectModel.getSelectTables().put(key,aliasMap.get(key).toString());
            }

            // 解析查询列表
            for(SQLSelectItem item : exportTableAliasVisitor.getSelectItems()){
                SelectModel.SelectColumns columns = new SelectModel.SelectColumns();
                columns.setColumnsName(((SQLPropertyExpr)item.getExpr()).getName());
                columns.setPrefix(((SQLIdentifierExpr)((SQLPropertyExpr)item.getExpr()).getOwner()).getName());
                columns.setAlias(item.getAlias());
                columns.setParent(selectModel);
                selectModel.getColumnsList().add(columns);
            }

            // 解析查询条件
            selectModel.getWhereColumns().addAll(exportTableAliasVisitor.getWhereColumns());
            selectModel.getWhereColumns().stream().forEach(item -> {
                item.setParent(selectModel);
            });
        }
        selectModel.setFormatContent(SQLUtils.format(sql,dbType));
        selectModel.setFormatContent(selectModel.getFormatContent().substring(0,selectModel.getFormatContent().lastIndexOf("WHERE")));
        return selectModel;
    }







}

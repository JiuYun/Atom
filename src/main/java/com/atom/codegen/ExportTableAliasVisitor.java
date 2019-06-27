package com.atom.codegen;


import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLSelectItem;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlSelectQueryBlock;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;
import com.atom.codegen.model.SelectModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportTableAliasVisitor extends MySqlASTVisitorAdapter {


    /***
     * 表别名信息
     */
    private Map<String, SQLTableSource> aliasMap    = new HashMap<String, SQLTableSource>();

    /***
     * 查询列明信息
     */
    private List<SQLSelectItem>         selectItems = new ArrayList<>();

    private List<SelectModel.WhereColumns> whereColumns = new ArrayList<>();


    @Override
    public boolean visit(SQLExprTableSource x) {
        String alias = x.getAlias();
        if(alias == null){
            aliasMap.put(x.getName().getSimpleName(), x);
        }else{
            aliasMap.put(alias, x);
        }
        return true;
    }

    @Override
    public boolean visit(MySqlSelectQueryBlock x) {
        super.visit(x);
        selectItems.addAll( x.getSelectList() );

        // 抽离查询条件
        if(x.getWhere() instanceof SQLBinaryOpExpr){
            final SQLBinaryOpExpr where = (SQLBinaryOpExpr)x.getWhere();
            List<SQLObject> sqlObjects = new ArrayList<SQLObject>(){{
                add(where.getLeft());
                add(where.getRight());
            }};

            List<SQLObject> next = new ArrayList<>();
            while(true){
                for(SQLObject object : sqlObjects){
                    if(object instanceof SQLBinaryOpExpr){
                        SQLBinaryOpExpr tem = (SQLBinaryOpExpr)object;
                        if(tem.getLeft() instanceof SQLBinaryOpExpr && tem.getRight() instanceof SQLBinaryOpExpr){
                            next.add(tem.getLeft());
                            next.add(tem.getRight());
                        }else{
                            SelectModel.WhereColumns whereColumn = new SelectModel.WhereColumns();
                            whereColumn.setOprator(((SQLBinaryOpExpr) object.getParent()).getOperator().getName());
                            whereColumn.setExpression(tem.toString());
                            whereColumns.add(whereColumn);
                        }
                    }
                }

                sqlObjects.clear();
                sqlObjects.addAll(next);
                next.clear();
                if(sqlObjects.size() == 0){
                    break;
                }
            }
        }









        return true;
    }


    public Map<String, SQLTableSource> getAliasMap() {
        return aliasMap;
    }

    public List<SQLSelectItem> getSelectItems() {
        return selectItems;
    }

    public List<SelectModel.WhereColumns> getWhereColumns() {
        return whereColumns;
    }
}

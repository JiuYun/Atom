package com.origin.atom.server;

import com.origin.atom.Model.TableModel;

import java.util.List;

/***
 *
 *  访问数据库查询所有表信息
 *
 *
 */
public interface ITableServer {


    /****
     * 返回数据库中所有的数据库表
     * @return
     */
    List<TableModel> tableModels();








}

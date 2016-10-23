package com.origin.atom.server;

import com.origin.atom.model.TableModel;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/***
 *
 *  访问数据库查询所有表信息
 *
 *
 */
public interface ITableServer {


    /****
     * 返回数据库中所有的数据库表
     * @param dataBaseName 			数据库名称
     * @return
     */
    List<TableModel> tableModels(@Param("dataBaseName")String dataBaseName);








}

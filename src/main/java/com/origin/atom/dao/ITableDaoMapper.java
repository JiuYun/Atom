package com.origin.atom.dao;

import com.origin.atom.Model.TableModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
@Repository
public interface ITableDaoMapper {

    List<TableModel> tables();







}

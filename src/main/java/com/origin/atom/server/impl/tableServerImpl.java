package com.origin.atom.server.impl;

import com.origin.atom.Model.TableModel;
import com.origin.atom.dao.ITableDaoMapper;
import com.origin.atom.server.ITableServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
@Service
public class tableServerImpl implements ITableServer{

    @Autowired
    private ITableDaoMapper tableDaoMapper;


    public List<TableModel> tableModels() {



        return tableDaoMapper.tables();
    }
}

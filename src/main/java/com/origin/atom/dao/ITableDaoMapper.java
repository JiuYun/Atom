package com.origin.atom.dao;


import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/17 0017.
 */
@Repository
public interface ITableDaoMapper {

    List<Map<String,String>> tables(String dataBaseName);






}

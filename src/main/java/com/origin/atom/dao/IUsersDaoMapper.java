package com.origin.atom.dao;

//import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
//@Repository("userDao")
public interface IUsersDaoMapper {


    public void saveUser(Map<String,Object> params);


}

package com.pojo;

import java.util.Date;

public class Users{

    private String userName;
    private Date createTime;
    private String email;
    private String password;
    private int id;


    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }

    public Date getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

}
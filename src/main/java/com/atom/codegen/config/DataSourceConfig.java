package com.atom.codegen.config;

/**
 * @author jion
 */
public class DataSourceConfig {

    /***
     * 驱动名称
      */
    private String driver;
    /**
     * 数据库地址
     */
    private String url;
    /**
     * 数据库用户名
     */
    private String userName;

    /***
     * 数据库密码
     */
    private String password;

    /***
     * 所选库
     */
    private String schema;

    /***
     * 数据库源名字
     */
    private String name;

    /***
     * 数据库类型
     */
    private String dataType;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}

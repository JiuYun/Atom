package com.atom.codegen.config;

/****
 * 生成文件包名
 *
 */
public class PackageConfig {

    private String daoPackageName;
    private String entityPackageName;
    private String xmlMapperPackageName;
    private String servicePackageName;
    private String servicePackageImplName;
    private String controllerPackageName;

    public String getDaoPackageName() {
        return daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getEntityPackageName() {
        return entityPackageName;
    }

    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }

    public String getXmlMapperPackageName() {
        return xmlMapperPackageName;
    }

    public void setXmlMapperPackageName(String xmlMapperPackageName) {
        this.xmlMapperPackageName = xmlMapperPackageName;
    }

    public String getServicePackageName() {
        return servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getServicePackageImplName() {
        return servicePackageImplName;
    }

    public void setServicePackageImplName(String servicePackageImplName) {
        this.servicePackageImplName = servicePackageImplName;
    }

    public String getControllerPackageName() {
        return controllerPackageName;
    }

    public void setControllerPackageName(String controllerPackageName) {
        this.controllerPackageName = controllerPackageName;
    }
}

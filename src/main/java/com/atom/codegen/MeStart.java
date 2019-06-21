package com.atom.codegen;

import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.config.PackageConfig;
import com.atom.codegen.start.Start;

/**
 * @author jion
 */
public class MeStart implements Start {

    @Override
    public PackageConfig packageConfig() {
        return new PackageConfig(){{
            setControllerPackageName("com.atom.test.controller");
            setDaoPackageName("com.atom.test.mapper");
            setEntityPackageName("com.atom.test.entity");
            setServicePackageName("com.atom.test.service");
            setServicePackageImplName("com.atom.test.service.impl");
            setXmlMapperPackageName("resources\\mapper\\");
        }};
    }

    @Override
    public DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig(){{
            setDriver("com.mysql.jdbc.Driver");
            setUrl("jdbc:mysql://localhost:3306/bluejingle");
            setUserName("root");
            setPassword("root");
            setSchema("bluejingle");
            setName("localhost");
            setDataType("mysql");
        }};
    }

    public static void main(String[] args) {
        Console.run(MeStart.class,args);
    }






}

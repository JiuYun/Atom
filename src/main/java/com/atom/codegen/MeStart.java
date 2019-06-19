//package com.atom.codegen;
//
//import com.atom.codegen.config.DataSourceConfig;
//import com.atom.codegen.config.PackageConfig;
//import com.atom.codegen.start.Start;
//
//public class MeStart implements Start {
//
//
//    @Override
//    public PackageConfig packageConfig() {
//        return new PackageConfig(){{
//            setControllerPackageName("com.atom.test.controller");
//            setDaoPackageName("com.atom.test.dao");
//            setEntityPackageName("com.atom.test.entity");
//            setServicePackageName("com.atom.test.service");
//            setServicePackageImplName("com.atom.test.service.impl");
//            setXmlMapperPackageName("resources\\mapper\\");
//        }};
//    }
//
//    @Override
//    public DataSourceConfig dataSourceConfig() {
//        return new DataSourceConfig(){{
//            setDriver("com.mysql.jdbc.Driver");
//            setUrl("jdbc:mysql://localhost:3306/blog");
//            setUserName("root");
//            setPassword("root");
//            setSchema("blog");
//            setName("localhost");
//            setDataType("mysql");
//        }};
//    }
//
//
//}

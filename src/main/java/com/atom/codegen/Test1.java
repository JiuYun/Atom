package com.atom.codegen;


import com.atom.codegen.config.DataSourceConfig;
import com.atom.codegen.core.Ioc;
import com.atom.codegen.db.LoadTableInfo;
import com.atom.codegen.model.Component;
import com.atom.codegen.model.TableModel;
import com.generator.me.FormatClassName;
import com.origin.atom.FileUtil.FileUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Test1 {


    public static void main(String[] args) throws Exception {
        List<Component> componentList = new ArrayList<Component>();

        Component xmlMapper = new Component("/template/xmlMapper/Mapper.bt");
        xmlMapper.setName("xmlMapper");
        xmlMapper.setOutFilePath("D:\\test\\%sMapper.xml");
        componentList.add(xmlMapper);

        Component dao = new Component("/template/mapper/Dao.bt");
        dao.setName("dao");dao.setOutFilePath("D:\\test\\%sMapper.java");
        componentList.add(dao);

        Component entity = new Component("/template/entity/Entity.bt");
        entity.setName("entity");
        entity.setOutFilePath("D:\\test\\%s.java");
        componentList.add(entity);

        Component service = new Component("/template/service/Service.bt");
        service.setName("service");
        service.setOutFilePath("D:\\test\\%sService.java");
        componentList.add(service);

        Component serviceImpl = new Component("/template/serviceImpl/ServiceImpl.bt");
        serviceImpl.setName("serviceImpl");
        serviceImpl.setOutFilePath("D:\\test\\%sServiceImpl.java");
        componentList.add(serviceImpl);

        // 1.读取配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig(){{
            setDriver("com.mysql.jdbc.Driver");
            setUrl("jdbc:mysql://localhost:3306/blog");
            setUserName("root");
            setPassword("root");
            setSchema("blog");
            setName("localhost");
            setDataType("mysql");
        }};
        Ioc.addBean("dataSourceConfig",dataSourceConfig);

        // 2.转换数据库表到内存中
        LoadTableInfo loadTableInfo = new LoadTableInfo();
        loadTableInfo.loadDataTableInfo();

        // 3.读取模板文件、使用模板
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        gt.registerFunctionPackage("MeFunction",FormatClassName.class);

        Map<String,TableModel> tableMap = (Map<String, TableModel>) Ioc.getBean("tableMap");

        FormatClassName fcn = new FormatClassName();
        Iterator<Component> iterator = componentList.iterator();
        while (iterator.hasNext()){
            Component component = iterator.next();
            Template t = gt.getTemplate(component.getTplFilePath());
            for (String key : tableMap.keySet()) {
                TableModel model = tableMap.get(key);
                t.binding("table",model);
                t.binding("className",model.getName());
                t.binding("columns",model.getColumn());
                t.binding("packageInfo","com.qianyun.core");
                String str      = t.render();
                String filePath = String.format(component.getOutFilePath(),fcn.formatClassName(model.getName()));
                String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
                String path     = filePath.substring(0,filePath.lastIndexOf("\\"));

                FileUtil.saveToFile(path,fileName,str,false);
            }
        }
    }




}

package com.atom.codegen;


import com.atom.codegen.command.Gen;
import com.atom.codegen.config.PackageConfig;
import com.atom.codegen.core.Ioc;
import com.atom.codegen.core.LoadProjectInfo;
import com.atom.codegen.db.LoadTableInfo;
import com.atom.codegen.model.Component;
import com.atom.codegen.start.Start;

import java.util.*;

/***
 *
 * 控制台
 * 代码生成器入口
 *
 */
public class Console {


    /***
     * 程序启动
     *
     * @param primarySource
     * @param args
     */
    public static void run(Class<?> primarySource, String[] args){
        Start start = null;
        try {
            start = (Start) primarySource.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Ioc.addBean("dataSourceConfig",start.dataSourceConfig());
        Ioc.addBean("packageConfig",start.packageConfig());
        loadSysTemp();

        // 2.转换数据库表到内存中
        try {
            LoadTableInfo loadTableInfo = new LoadTableInfo();
            loadTableInfo.loadDataTableInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 1.准备接受指令
        Scanner scanner = new Scanner(System.in);
        System.out.println("----------------------------欢迎使用--------------------------");
        String input;
        while ((input = scanner.nextLine()) != null){
            if(input.equals("exist")){
                System.out.println("bye~");
                break;
            }
            String[] params = input.split(" ");
            switch (params[0]){
                case "gen":
                    Gen gen = new Gen();
                    gen.execute(params);
                    break;
                case "projectInfo":
                    LoadProjectInfo.projectInfo();
                    break;
                default:
                    System.out.println("暂时不支持该操作");
                    break;
            }
        }
    }



    public static void loadSysTemp(){
        PackageConfig pConfig = (PackageConfig) Ioc.getBean("packageConfig");

        // 1.初始化模板信息
        List<Component> componentList = new ArrayList<Component>();
        Component xmlMapper = new Component("/template/xmlMapper/Mapper.bt");
        xmlMapper.setName("xmlMapper");
        xmlMapper.setOutFilePath(LoadProjectInfo.projectBase() + pConfig.getXmlMapperPackageName().replace(".","\\") + "\\"+ "%sMapper.xml");
        componentList.add(xmlMapper);

        Component dao = new Component("/template/mapper/Dao.bt");
        dao.setName("dao");
        dao.setOutFilePath(LoadProjectInfo.projectBase() + "\\java\\" + pConfig.getDaoPackageName().replace(".","\\") + "\\"+ "%sMapper.java");
        componentList.add(dao);

        Component entity = new Component("/template/entity/Entity.bt");
        entity.setName("entity");
        entity.setOutFilePath(LoadProjectInfo.projectBase() + "\\java\\" + pConfig.getEntityPackageName().replace(".","\\") + "\\"+ "%s.java");
        componentList.add(entity);

        Component service = new Component("/template/service/Service.bt");
        service.setName("service");
        service.setOutFilePath(LoadProjectInfo.projectBase() + "\\java\\" + pConfig.getServicePackageName().replace(".","\\") + "\\"+ "%sService.java");
        componentList.add(service);

        Component serviceImpl = new Component("/template/serviceImpl/ServiceImpl.bt");
        serviceImpl.setName("serviceImpl");
        serviceImpl.setOutFilePath(LoadProjectInfo.projectBase() + "\\java\\" + pConfig.getServicePackageImplName().replace(".","\\") + "\\"+ "%sServiceImpl.java");
        componentList.add(serviceImpl);
        Ioc.addBean("componentList",componentList);
    }


































}

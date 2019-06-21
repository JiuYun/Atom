package com.atom.codegen.core;

import com.atom.codegen.config.PackageConfig;
import com.atom.codegen.model.Component;
import com.atom.codegen.model.TableModel;
import com.generator.me.FormatClassName;
import com.origin.atom.FileUtil.FileUtil;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 *
 * 渲染出文件内容
 *
 *
 */
public class Renderer {
    GroupTemplate gt = null;

    private Renderer(){
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        try {
            Configuration cfg = Configuration.defaultConfiguration();
            gt = new GroupTemplate(resourceLoader, cfg);
            gt.registerFunctionPackage("MeFunction",FormatClassName.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class RendererInstance{
        private static final Renderer render = new Renderer();
    }

    public static Renderer getInstance(){
        return RendererInstance.render;
    }


    public void render(Component component,TableModel model){
        FormatClassName fcn = new FormatClassName();
        Template t = gt.getTemplate(component.getTplFilePath());
        t.binding("table",model);
        t.binding("className",model.getName());
        t.binding("columns",model.getColumn());

        PackageConfig pConfig = (PackageConfig) Ioc.getBean("packageConfig");
        t.binding("controllerPackage",pConfig.getControllerPackageName());
        t.binding("daoPackage",pConfig.getDaoPackageName());
        t.binding("entityPackage",pConfig.getEntityPackageName());
        t.binding("servicePackage",pConfig.getServicePackageName());
        t.binding("serviceImplPackage",pConfig.getServicePackageImplName());

        String str      = t.render();
        String filePath = String.format(component.getOutFilePath(),fcn.formatClassName(model.getName()));
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        String path     = filePath.substring(0,filePath.lastIndexOf("\\"));

        try {
            FileUtil.saveToFile(path,fileName,str,false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Component component,String tableName){
        Map<String,TableModel>  tableMap    = (Map<String, TableModel>) Ioc.getBean("tableMap");
        List<TableModel>        models      = null;
        if(tableName == null){
            models = new ArrayList<>(tableMap.values());
        }else{
            models = new ArrayList<>();
            models.add(tableMap.get(tableName));
        }
        for (TableModel model : models){
            render(component,model);
        }
    }





    public void registerFunctionPackage(String packageName,Class className){
        gt.registerFunctionPackage(packageName,className);
    }













}

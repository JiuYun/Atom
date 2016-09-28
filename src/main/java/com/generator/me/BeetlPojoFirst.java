package com.generator.me;

import com.qiandu.Generator.Model.ColumnModel;
import com.qiandu.Generator.Model.TableModel;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/20 0020.
 */
public class BeetlPojoFirst {

    public static void main(String[] args) throws IOException {

        TableModel tableModel = new TableModel();
        tableModel.setName("student_class");
        Set<ColumnModel> columns = new HashSet<ColumnModel>();
        tableModel.setColumn(columns);
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName("student_name");
        columnModel.setDataType("String");
        columnModel.setNotes("学生姓名");
        columnModel.setPk(true);
        columns.add(columnModel);
        ColumnModel columnMode2 = new ColumnModel();
        columnMode2.setColumnName("student_email");
        columnMode2.setDataType("String");
        columnMode2.setNotes("学生邮件地址");
        columnMode2.setPk(false);
        columns.add(columnMode2);

        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        gt.registerFunctionPackage("MeFunction",FromatClassName.class);
        Template t = gt.getTemplate("/JavaPojo.bt");
        t.binding("className",tableModel.getName());
        t.binding("columns",tableModel.getColumn());
        String str = t.render();
        System.out.println(str);







    }



}

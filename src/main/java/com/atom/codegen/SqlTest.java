package com.atom.codegen;


import com.atom.codegen.model.SelectModel;
import com.generator.me.FormatClassName;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;


public class SqlTest {



    public static void main(String[] args) {

        String sql= "select\n" +
        "            tcs.`name`  as systemName,\n" +
        "            tcit.`name` as featuresName,\n" +
        "            tcp.`name`\tas projectName,\n" +
        "            tci.reference,\n" +
        "            tci.design_value\n" +
        "        from tbl_check_info tci\n" +
        "        join tbl_check_project tcp on tci.check_project_id = tcp.id\n" +
        "        join tbl_check_system tcs on tcs.id = tci.check_system_id\n" +
        "        join tbl_check_item tcit on tcit.id = tci.check_item_id" +
        " where (tcs.`name` like concat('%',?,'%') and tcit.`name` = ?) and tcp.`name` = ? and tci.reference = ? order by tci.`name`";


        SelectModel selectModel = SqlUtils.selectSqlToSelectModel(sql);


        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        GroupTemplate gt = null;
        try {
            Configuration cfg = Configuration.defaultConfiguration();
            gt = new GroupTemplate(resourceLoader, cfg);
            gt.registerFunctionPackage("MeFunction",FormatClassName.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Template t = gt.getTemplate("/template/xmlMapper/base/selectFunction.bt");
        t.binding("selectModel",selectModel);

        String str      = t.render();
        System.out.println(str);
    }
}



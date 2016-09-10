package com.qiandu.Generator.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.qiandu.Generator.Model.ColumnModel;
import com.qiandu.Generator.Model.TableModel;
import com.qiandu.Generator.constant.Constant;

/***
 * 生成JavaBean类
 * @author Martin
 * @Data 2015-4-23
 *
 */
public class BeanUtil {

    /***
     * 生成pojo
     */
    public static void doPojo(Map<String,TableModel> tableMap){


        String path = "D:\\Project\\com.qiandu.Generator.core\\src\\com\\qiandu\\pojo\\";
        TableModel model = null;
        for (String key : tableMap.keySet()) {
            StringBuffer sbpackage    = new StringBuffer( "" );//包部分
            StringBuffer sbimprot     = new StringBuffer( "" );//导包部分
            StringBuffer sbclass      = new StringBuffer( "" );//class 开头
            StringBuffer sbparams     = new StringBuffer( "" );//类的属性部分
            StringBuffer sbstructure  = new StringBuffer( "" );//有参构造
            StringBuffer sbstructure0 = new StringBuffer( "" );//有参数构造的构造参数体
            StringBuffer sbstructure1 = new StringBuffer( "" );//有参数构造体
            StringBuffer sbstructure2 = new StringBuffer( "" );//无参数构造
            StringBuffer sbGetSet     = new StringBuffer( "" );//Get 和Set
            Set<String>  set		  = new HashSet<String>();

            model = tableMap.get(key);

            //生成头部
            sbpackage.append("package com.qiandu.pojo;\r\r");

            //生成类名
            String className1 = model.getName().substring(0,1);
            String className2 = model.getName().substring(1);
            String className  = className1.toUpperCase()+className2;

            //文件名称
            String fileName = className+".java";

            //类开始
            sbclass.append("public class "+className+"{\r\r");

            //解析对应关系
            for (String key2 : tableMap.keySet()) {
                TableModel model2 = tableMap.get(key2);
                Iterator<ColumnModel> iterator = model2.getColumn().iterator();
                while (iterator.hasNext()) {
                    ColumnModel columnModel = iterator.next();
                    if(className.equals(columnModel.getDataType())){
                        String className3 = model2.getName().substring(0,1);
                        String className4 = model2.getName().substring(1);
                        String className5  = className3.toUpperCase()+className4;
                        String type = "Set<"+className5+">";
                        ColumnModel e = new ColumnModel();
                        e.setColumnName(className5);
                        e.setDataType(type);
                        model.getColumn().add(e);
                    }

                }
            }


            //生成属性
            Iterator<ColumnModel> iterator = model.getColumn().iterator();
            ColumnModel columnModel = null;

            sbstructure.append("    public "+className+"(");//有参数构造
            sbstructure2 = new StringBuffer("");//有参数构造的构造体
            while (iterator.hasNext()) {
                columnModel = iterator.next();
                String type = Constant.DataTypeToJavaType(columnModel.getDataType());

                if (type == null || type.equals( "null" )) {
                    System.err.println(columnModel.getDataType());
                    type = columnModel.getDataType();
                }
                //记录包部分
                if(Constant.getPackage(columnModel.getDataType()) != null && !Constant.getPackage(columnModel.getDataType()).equals("") ){
                    set.add(Constant.getPackage(columnModel.getDataType()));
                }

                //生成属性名称
                String columnName1 = columnModel.getColumnName().substring(0,1);
                String columnName2 = columnModel.getColumnName().substring(1);
                String paramsName  =  columnName1.toLowerCase()+columnName2;
                sbparams.append("    private "+type+" "+paramsName+";//"+columnModel.getNotes()+"\r\r");


                //生成Get
                sbGetSet.append("    public "+type+" get"+paramsName+" (){\r");
                sbGetSet.append("        return this."+paramsName+";\r");
                sbGetSet.append("    }\r\r");
                //生成Set
                sbGetSet.append("    public void set"+paramsName+" ("+type+" "+paramsName+"){\r");
                sbGetSet.append("        this."+paramsName+" = "+paramsName+";\r");
                sbGetSet.append("    }\r\r");

                //生成有参数构造方法参数
                sbstructure0.append(type+" "+paramsName+",");
                //生成有参数构造的方法体
                sbstructure1.append("        this."+paramsName+"\t=\t"+paramsName+";\r");
            }

            //生成无构造函数
            sbstructure2.append("\r    public "+className+"(){\r");
            sbstructure2.append("\t    //无参构造就这样..\r");
            sbstructure2.append("    }\r\r");

            //处理有参数构造函数的参数体的最后一个逗号
            sbstructure0 = new StringBuffer(sbstructure0.substring(0, sbstructure0.lastIndexOf(",")));

            //生成有参数构造
            sbstructure.append(sbstructure0);
            sbstructure.append("){\r");
            sbstructure.append(sbstructure1);
            sbstructure.append("    }\r\r");

            //生成导包部分
            Iterator<String> iterator2 = set.iterator();
            while (iterator2.hasNext()) {
                sbimprot.append("import "+iterator2.next()+";\r\r");
            }

            //生成文件类容
            StringBuffer content = new StringBuffer("");
            content.append(sbpackage);
            content.append(sbimprot);
            content.append(sbclass);
            content.append(sbparams);
            content.append(sbstructure2);
            content.append(sbstructure);
            content.append(sbGetSet);
            content.append("}\r\r");

            //保存到文件
            File file = new File(path+fileName);
            try {
                PrintStream printStream = new PrintStream(file);
                printStream.print(content.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }




    /***
     * 生成Dao
     */
    public static void doDao(){

    }

    /***
     * 生成服务
     */
    public static void doServer(){

    }






}

package com.atom.codegen.core;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/***
 *
 * 加载当前项目信息
 *
 */
public class LoadProjectInfo {


    public static String projectBase(){
        return System.getProperty("user.dir") + "\\" + "src\\main\\java\\";
    }

    public static Map<String,Object> projectInfo(){
        // 遍历当前项目下面所有的包
        String  basePath    = System.getProperty("user.dir");
        File    file        = new File(basePath);

        List<File> folders  = new ArrayList<>();
        List<File> folderTmp= new ArrayList<>();
        folders.addAll(Arrays.asList(file.listFiles()));

        while (true){
            for(File f : folders){
                if(f.getPath().indexOf("\\.") > 0){
                    continue;
                }
                if(f.isDirectory()){
                    System.out.println(f.getAbsolutePath());
                    folderTmp.addAll(Arrays.asList(f.listFiles()));
                }
            }

            folders.clear();
            folders.addAll(folderTmp);

            if(folders.size() == 0){
                break;
            }
            folderTmp.clear();
        }
        return null;
    }













}

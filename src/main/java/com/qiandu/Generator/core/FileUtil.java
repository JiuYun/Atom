package com.qiandu.Generator.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 *
 *
 *
 *
 *
 */
public class FileUtil {


    /***
     * 保存字符串到文件中
     * @param path                  文件路径
     * @param fileName              文件名称
     * @param content               文件内容
     */
    public static void saveStringToFile(String path,String fileName,String content){
        File file = new File(path+fileName);
        try {
            PrintStream printStream = new PrintStream(file);
            printStream.print(content.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}

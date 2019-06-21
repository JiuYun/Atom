package com.origin.atom.FileUtil;

import java.io.*;


/***
 *
 * 将字符串保存到文件中
 *
 */
public class FileUtil {


    /***
     * 读取文件内容
     * @param   filePath
     * @param   fileName
     * @return
     */
    public static String readFile(String filePath,String fileName) throws IOException {
        File file = new File(filePath+File.separator+fileName);
        if(!file.exists()){throw new FileNotFoundException("文件 "+filePath+File.separator+fileName+"未找到");}
        BufferedReader          bf          = null;
        InputStreamReader       isr         = null;
        FileInputStream         fis         = null;
        StringBuffer            content     = null;
        String                  readLine    = null;
        try {
            fis     = new FileInputStream(file);
            isr     = new InputStreamReader(fis);
            bf      = new BufferedReader(isr);
            content = new StringBuffer("");
            while ((readLine = bf.readLine()) != null){
                content.append(readLine+"\r");
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(bf   != null){bf.close();}
            if(isr  != null){isr.close();}
            if(fis  != null){fis.close();}
        }
        return content.toString();
    }


    /***
     *
     * 使用Java.io的字符流读取文件
     *
     * @param filePath                  文件路径
     * @param fileName                  文件名称
     */
    public static String readFileByReader(String filePath,String fileName) throws IOException {
        File file = new File(filePath + File.separator + fileName);
        if(!file.exists()){throw new FileNotFoundException("文件 "+filePath+File.separator+fileName+"未找到");}
        FileInputStream     fis             = null;
        InputStreamReader   isr             = null;
        StringBuffer        fileContent     = null;
        try{
            fileContent = new StringBuffer("");
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            char[] temp = new char[1024];
            String stemp = null;
            while (isr.read(temp) != -1){
                stemp = new String(temp);
                fileContent.append(stemp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(isr != null){isr.close();}
            if(fis != null){fis.close();}
        }
        return fileContent.toString();
    }


    public static String readFileByFileInputStream(String filePath,String fileName) throws IOException {
        File file = new File(filePath + File.separator + fileName);
        if(!file.exists()){throw new FileNotFoundException();}
        FileInputStream fis         = null;
        StringBuffer    content     = null;
        try{
            fis     = new FileInputStream(file);
            content = new StringBuffer("");
            byte[] temp = new byte[1024];
            while (fis.read(temp) != -1){
                content.append(new String(temp));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(fis != null){fis.close();}
        }
        return content.toString();
    }


    public static String readFileByFileReader(String filePath,String fileName) throws IOException {
        File file = new File(filePath + File.separator + fileName);
        if(!file.exists()){throw new FileNotFoundException();}
        FileReader      fr          = null;
        StringBuffer    content     = null;
        try {
            fr          = new FileReader(file);
            content     = new StringBuffer("");
            char[] temp = new char[1024];
            while (fr.read(temp) != -1){
                content.append(temp);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(fr != null){fr.close();}
        }
        return content.toString();
    }


    /****
     * 写入类容到文件</br>
     *
     * 如果文件不存在就创建它，如果文件夹不存在就创建它
     *
     * @param filePath
     * @param fileName
     * @param content
     * @return
     */
    public static boolean saveToFile(String filePath,String fileName,String content,boolean append) throws IOException {
        File    fileDir     = new File(filePath);
        File    file        = null;
        if(!fileDir.exists() || !fileDir.isDirectory()){
            fileDir.mkdirs();
            file = new File(filePath + File.separator + fileName);
            file.createNewFile();
        }else{
            file = new File(filePath + File.separator + fileName);
            if(!file.exists()){file.createNewFile();}{
                System.out.println(file.getAbsolutePath() + " 已存在,跳过生成 ");
                return false;
            }
        }
        FileOutputStream    fos = null;
        OutputStreamWriter  osw = null;
        try{
            fos = new FileOutputStream(file,append);
            osw = new OutputStreamWriter(fos);
            osw.write(content);
            osw.flush();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(osw != null){osw.close();}
            if(fos != null){fos.close();}
        }
        return true;
    }

    /***
     *
     * 检查文件是否存在
     *
     * @param path                      文件路径
     * @param fileName                  文件名称
     * @return
     */
    public static boolean existsFile(String path,String fileName){
        File file = new File(path + File.separator + fileName);
        return file.exists();
    }






    //如果文件夹都是新创建的那么文件肯定也是新创建,文件写入方式也就是重新写入







    public static void main(String[] args) throws IOException {
        System.out.println(FileUtil.readFileByReader("C:\\Users\\Administrator\\Desktop\\layui-v1.0.3","更新日志.txt"));
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(FileUtil.readFile("C:\\Users\\Administrator\\Desktop\\layui-v1.0.3","更新日志.txt"));
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(FileUtil.readFileByFileInputStream("C:\\Users\\Administrator\\Desktop\\layui-v1.0.3","更新日志.txt"));
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(FileUtil.readFileByFileReader("C:\\Users\\Administrator\\Desktop\\layui-v1.0.3","更新日志.txt"));

    }


}

package com.atom.codegen;


import com.atom.codegen.command.Gen;

import java.util.Scanner;

/***
 *
 * 控制台
 * 代码生成器入口
 *
 */
public class Console {


    /****
     *
     * 先给自己来个main方法
     *
     * @param args
     */
    public static void main(String[] args) {

        // 0.初始化项目基础信息




        // 1.初始化模板信息




        // 2.初始化数据库信息




        // 3.准备接受指令




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
                default:
                    System.out.println("暂时不支持该操作");
                    break;
            }
        }
    }

}

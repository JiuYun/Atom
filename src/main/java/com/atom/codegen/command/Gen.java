package com.atom.codegen.command;

/***
 *
 */
public class Gen implements Command {


    public void execute(String[] args) {
        switch (args[2]){
            case "search":
                System.out.println("search 操作");
                break;
            case "init":
                System.out.println("初始化 操作");
                break;
            case "update" :
                System.out.println("update 操作");
                break;
            case "insert" :
                System.out.println("insert 操作");
                break;
            case "del" :
                System.out.println("生成删除相关的代码");
                break;
            default:
                System.out.println("不支持的方式");
                break;
        }
    }



}

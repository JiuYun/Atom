package com.atom.codegen.command;

import com.atom.codegen.core.Ioc;
import com.atom.codegen.core.Renderer;
import com.atom.codegen.model.Component;

import java.util.List;

/***
 *
 * @author jion
 */
public class Gen implements Command {

    private Renderer renderer = Renderer.getInstance();

    @Override
    public void execute(String[] args) {
        String operate      = args.length == 2 ? args[1] : args[2];
        String tableName    = args.length == 2 ? null : args[1];
        switch (operate){
            case "search":

                System.out.println("search 操作");
                break;
            case "init":
                List<Component> components = (List<Component>) Ioc.getBean("componentList");
                for (Component component : components){
                    renderer.render(component, tableName);
                }
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

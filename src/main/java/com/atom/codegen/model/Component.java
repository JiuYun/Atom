package com.atom.codegen.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;

/***
 * 定义组件
 */
public class Component implements Serializable {

    private String name;                            // 组件名称
    private String outFilePath;                     // 输出文件地址
    private String tplFilePath;                     // 模板文件地址
    private LinkedList<Component> childComponent;   // 子组件
    private Map<String,Object> params;              // 参数

    public Component() {
    }

    public Component(String tplFilePath) {
        this.name = tplFilePath.substring(tplFilePath.lastIndexOf("/"),tplFilePath.lastIndexOf("."));
        this.tplFilePath = tplFilePath;
    }

    public String getOutFilePath() {
        return outFilePath;
    }

    public void setOutFilePath(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public String getTplFilePath() {
        return tplFilePath;
    }

    public void setTplFilePath(String tplFilePath) {
        this.tplFilePath = tplFilePath;
    }

    public LinkedList<Component> getChildComponent() {
        return childComponent;
    }

    public void setChildComponent(LinkedList<Component> childComponent) {
        this.childComponent = childComponent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}

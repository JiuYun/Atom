package com.atom.codegen.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/****
 * 程序的仓库
 * @author Administrator
 *
 */
public final class Ioc implements Serializable{

    private static final long serialVersionUID = 5637958809315682569L;

    private static Map<String,Object> map = new HashMap<String,Object>();//IOC 集合

    /****
     * 私有构造函数防止被实例化
     */
    private Ioc(){}


    /****
     * 添加元素到Bean
     * @param key
     * @param object
     */
    public static void addBean(String key,Object object){
        if(map.containsKey(key)){
            throw new RuntimeException("%s重复添加");
        }else{
            map.put(key, object);
        }
    }


    /****
     * 获取Bean
     * @param key
     * @return
     */
    public static Object getBean(String key){
        Object object = map.get(key);
        if (object == null) {
            throw new NullPointerException(String.format("%s不存在",key));
        }
        return object;
    }










}

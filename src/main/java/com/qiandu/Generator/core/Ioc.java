package com.qiandu.Generator.core;

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
     * @param Key
     * @param object
     */
    public static void addBean(String key,Object object){
        System.out.println("添加:"+key);
        if (getBean(key) != null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("警告！元素已存在");
            }
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
        System.out.println("获取:"+key);
        Object object = map.get(key);
        if (object == null) {
            try {
                throw new Exception();
            } catch (Exception e) {
                System.out.println("警告！没有"+key+"对象");
            }
        }
        return object;
    };










}

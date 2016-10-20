package com.qiandu.Generator.interaction;



import com.qiandu.Generator.core.Entrance;
public class Interaction {


    public static void main(String[] args) {
        Entrance entrance = new Entrance();
        try {
            entrance.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

package com.atom.codegen;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {


    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("C:\\Users\\jion\\Desktop\\AccountMapper.xml", "rw");

        byte[] content = new byte[1024];
        while (raf.read(content) != -1){

            System.out.println(new String(content,"utf-8"));
        }









    }


}

package com.thinkit.microservicecloud.controller;

import java.io.*;

public class tran {

    public static void main(String[] args) {

       File newFile = new File("D:/pcm/test.wav");
       File oldFile = new File("D:/pcm/testbak.wav");


       System.out.println(newFile.length());
       System.out.println(oldFile.length());

       FileInputStream fis = null ;
       int offset = Math.toIntExact(newFile.length() - oldFile.length());

       System.out.println(offset);
       byte[]  buff = new byte[offset];

        try {
            fis =  new FileInputStream(newFile);

            fis.skip(oldFile.length());

            fis.read(buff);


        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

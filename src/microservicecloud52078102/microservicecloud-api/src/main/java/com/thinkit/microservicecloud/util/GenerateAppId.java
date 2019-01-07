package com.thinkit.microservicecloud.util;

public class GenerateAppId {

    public static String getAppId(){

        String uuid = java.util.UUID.randomUUID().toString();


       String sub =  uuid.substring(0,8);

        return sub;
    }
}

package com.thinkit.microservicecloud.entities.console;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MyApps {
    private int id;
    private String appid;
    private String appname;
    private String appkey;
    private int count; //App 下多少个服务

    @Override
    public String toString() {
        return "MyApps{" +
                "id=" + id +
                ", appid='" + appid + '\'' +
                ", appname='" + appname + '\'' +
                ", appkey='" + appkey + '\'' +
                ", count=" + count +
                '}';
    }
}

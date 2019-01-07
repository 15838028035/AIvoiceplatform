package com.thinkit.microservicecloud.entities.console;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Date;

@Data
@NoArgsConstructor
public class UserApp {
    private int id;
    private String appid;
    private String appkey;
    private String  appname;
    private String field;
    private String platform;
    private String description;
    private Date createTime;
    private int userid;

    private int[] productIds;

    @Override
    public String toString() {
        return "UserApp{" +
                "id=" + id +
                ", appid='" + appid + '\'' +
                ", appkey='" + appkey + '\'' +
                ", appname='" + appname + '\'' +
                ", field='" + field + '\'' +
                ", platform='" + platform + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", userid=" + userid +
                ", productIds=" + Arrays.toString(productIds) +
                '}';
    }
}

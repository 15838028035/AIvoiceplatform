package com.thinkit.microservicecloud.entities.console;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class AppService {
    private int serviceid;
    private int productid;
    private Date creatTime;
    private double indate;
    private String state;
    private int qps;
    private String secret_key;
    private int appid;

    @Override
    public String toString() {
        return "AppService{" +
                "serviceid=" + serviceid +
                ", productid=" + productid +
                ", creatTime=" + creatTime +
                ", indate=" + indate +
                ", state='" + state + '\'' +
                ", qps=" + qps +
                ", appid=" + appid +
                '}';
    }
}

package com.thinkit.microservicecloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthInfo {
    private String secret_key;
    private int qps;
    private Date creatTime;
    private int indate;

    @Override
    public String toString() {
        return "AuthInfo{" +
                "secret_key='" + secret_key + '\'' +
                ", qps=" + qps +
                ", creatTime='" + creatTime + '\'' +
                ", indate=" + indate +
                '}';
    }
}

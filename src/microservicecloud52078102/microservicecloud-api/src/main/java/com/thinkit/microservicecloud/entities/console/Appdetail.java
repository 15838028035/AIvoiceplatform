package com.thinkit.microservicecloud.entities.console;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Appdetail {
    private String product_name;
    private String product_type;
    private String creatTime;
    private double indate;
    private String state;
    private int qps;
    private String secret_key;

    @Override
    public String toString() {
        return "Appdetail{" +
                "product_name='" + product_name + '\'' +
                ", product_type='" + product_type + '\'' +
                ", creatTime=" + creatTime +
                ", indate=" + indate +
                ", state='" + state + '\'' +
                ", qps=" + qps +
                ", secret_key='" + secret_key + '\'' +
                '}';
    }
}

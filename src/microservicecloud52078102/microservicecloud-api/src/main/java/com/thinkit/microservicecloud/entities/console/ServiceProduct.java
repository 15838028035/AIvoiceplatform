package com.thinkit.microservicecloud.entities.console;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceProduct {
    private int id;
    private int typeid;
    private String product_name;
    private String product_add;
    private double default_day;
    private int default_qps;
    private int default_servicecall;

    @Override
    public String toString() {
        return "ServiceProduct{" +
                "id=" + id +
                ", typeid=" + typeid +
                ", product_name='" + product_name + '\'' +
                ", product_add='" + product_add + '\'' +
                ", default_day=" + default_day +
                ", default_qps=" + default_qps +
                ", default_servicecall=" + default_servicecall +
                '}';
    }
}

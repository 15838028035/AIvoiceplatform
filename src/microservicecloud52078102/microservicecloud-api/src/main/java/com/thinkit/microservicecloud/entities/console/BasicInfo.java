package com.thinkit.microservicecloud.entities.console;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicInfo {
    private int id;
    private String username;
    private String property;
    private String primary_industry;
    private String secondary_industry;
    private String main_business;
    private String website;
    private int userid;


    @Override
    public String toString() {
        return "BasicInfo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", property='" + property + '\'' +
                ", primary_industry='" + primary_industry + '\'' +
                ", secondary_industry='" + secondary_industry + '\'' +
                ", main_business='" + main_business + '\'' +
                ", website='" + website + '\'' +
                ", userid=" + userid +
                '}';
    }
}

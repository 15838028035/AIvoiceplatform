package com.thinkit.microservicecloud.entities.console;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Company_Certificate {

    private int id;
    private String company_name;
    private String company_website;
    private String company_field;
    private String business_license;
    private String address;
    private String social_credit_code;
    private String contacts;
    private String contact_number;
    private int status;
    private Date creatTime;
    private Date opTime;
    private String column1;
    private String column2;
    private String column3;

    @Override
    public String toString() {
        return "Company_Certificate{" +
                "id=" + id +
                ", company_name='" + company_name + '\'' +
                ", company_website='" + company_website + '\'' +
                ", company_field='" + company_field + '\'' +
                ", business_license='" + business_license + '\'' +
                ", address='" + address + '\'' +
                ", social_credit_code='" + social_credit_code + '\'' +
                ", contacts='" + contacts + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", status=" + status +
                ", creatTime=" + creatTime +
                ", opTime=" + opTime +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                '}';
    }
}
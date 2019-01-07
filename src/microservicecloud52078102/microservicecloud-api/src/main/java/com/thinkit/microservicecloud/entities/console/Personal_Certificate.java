package com.thinkit.microservicecloud.entities.console;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Personal_Certificate {

    private int id;
    private String ID_number;
    private String realName;
    private String photo1;
    private String photo2;
    private String address;
    private int user_id;
    private int company_id;
    private int status;
    private Date creatTime;
    private Date opTime;
    private String column1;
    private String column2;
    private String column3;

    @Override
    public String toString() {
        return "Personal_Certificate{" +
                "id=" + id +
                ", ID_number='" + ID_number + '\'' +
                ", realName='" + realName + '\'' +
                ", photo1='" + photo1 + '\'' +
                ", photo2='" + photo2 + '\'' +
                ", address='" + address + '\'' +
                ", user_id=" + user_id +
                ", company_id=" + company_id +
                ", status=" + status +
                ", creatTime=" + creatTime +
                ", opTime=" + opTime +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                '}';
    }
}
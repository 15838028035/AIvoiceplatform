package com.thinkit.microservicecloud.entities.userlogin;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Data
public class User {

    private long userid;
    private String username;
    private String password;
    private  String phone;
    private String email;
    private Date creatTime;

    private String column1;
    private String column2;
    private String column3;

    @Override
    public String toString() {
        return "User{" +
                "suerid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", creatTime=" + creatTime +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                '}';
    }
}

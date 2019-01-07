package com.thinkit.microservicecloud.entities.userlogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRegister {

    private int userid;
    private String username;
    private String password;
    private  String phone;
    private String email;
    private String code; // 验证码


    @Override
    public String toString() {
        return "UserRegister{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

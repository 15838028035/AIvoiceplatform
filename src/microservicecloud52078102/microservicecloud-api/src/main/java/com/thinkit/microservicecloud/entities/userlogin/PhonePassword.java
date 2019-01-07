package com.thinkit.microservicecloud.entities.userlogin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhonePassword {

    private String phone;
    private String password;

    @Override
    public String toString() {
        return "PhonePassword{" +
                "phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

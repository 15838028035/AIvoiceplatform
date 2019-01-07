package com.thinkit.microservicecloud.entities.userlogin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PhoneCode {
    private String phone;
    private String code;

    @Override
    public String toString() {
        return "PhoneCode{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

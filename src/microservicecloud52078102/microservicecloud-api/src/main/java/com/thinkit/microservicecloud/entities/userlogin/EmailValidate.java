package com.thinkit.microservicecloud.entities.userlogin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailValidate {

    private String email;

    @Override
    public String toString() {
        return "EmailValidate{" +
                "email='" + email + '\'' +
                '}';
    }
}

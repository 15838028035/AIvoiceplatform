package com.thinkit.microservicecloud.entities.userlogin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultInfo {
    private  String code;
    private  String login;
    private  String msg;
    private  String result;


    @Override
    public String toString() {
        return "ResultInfo{" +
                "code='" + code + '\'' +
                ", login='" + login + '\'' +
                ", msg='" + msg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

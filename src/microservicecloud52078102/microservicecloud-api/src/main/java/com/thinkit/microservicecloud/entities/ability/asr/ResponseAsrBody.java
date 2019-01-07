package com.thinkit.microservicecloud.entities.ability.asr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAsrBody {
    private int err_code;    // 错误码
    private String err_msg;  // 错误码描述
    private String result;   // 识别结果


    @Override
    public String toString() {
        return "ResponseAsrBody{" +
                "err_code=" + err_code +
                ", err_msg='" + err_msg + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

package com.thinkit.microservicecloud.entities.ability.asr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAsrBody {
    private String cuid;      // 用户唯一标识， 可以使用appid
    private String sid;
    private String auth;
    private int idx;
    private boolean islast;
    private byte[] data;
    private int channel;      // 声道数，仅支持单声道，固定值 1
    private String format;    // 语音格式 pcm wav 不区分大小写
    private int rate;         // 采样率 8000Hz

    @Override
    public String toString() {
        return "RequestAsrBody{" +
                "cuid='" + cuid + '\'' +
                ", sid='" + sid + '\'' +
                ", auth='" + auth + '\'' +
                ", idx=" + idx +
                ", islast=" + islast +
                ", data=" + Arrays.toString(data) +
                ", channel=" + channel +
                ", format='" + format + '\'' +
                ", rate=" + rate +
                '}';
    }
}

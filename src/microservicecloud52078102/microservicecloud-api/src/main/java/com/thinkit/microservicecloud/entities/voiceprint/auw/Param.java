package com.thinkit.microservicecloud.entities.voiceprint.auw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Param {

    private String cmd;
    private String sid;
    private int audioStatus;
    private int syncid;
    private String data;

    @Override
    public String toString() {
        return "Param{" +
                "cmd='" + cmd + '\'' +
                ", sid='" + sid + '\'' +
                ", audioStatus=" + audioStatus +
                ", syncid=" + syncid +
                ", data='" + data + '\'' +
                '}';
    }
}

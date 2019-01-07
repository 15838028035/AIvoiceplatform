package com.thinkit.microservicecloud.entities.voiceprint.grs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Param {
    private String sid;
    private int syncid;
    private String cmd;

    @Override
    public String toString() {
        return "Param{" +
                "sid='" + sid + '\'' +
                ", syncid=" + syncid +
                ", cmd='" + cmd + '\'' +
                '}';
    }
}

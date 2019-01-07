package com.thinkit.microservicecloud.entities.voiceprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Param {
    private String cmd;
    private String spk_type;
    private String uid;

    @Override
    public String toString() {
        return "Param{" +
                "cmd='" + cmd + '\'' +
                ", spk_type='" + spk_type + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}

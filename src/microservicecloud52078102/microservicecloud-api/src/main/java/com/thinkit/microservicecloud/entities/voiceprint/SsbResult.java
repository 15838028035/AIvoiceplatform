package com.thinkit.microservicecloud.entities.voiceprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SsbResult {
    private int ret;
    private String sid;

    @Override
    public String toString() {
        return "SsbResult{" +
                "ret=" + ret +
                ", sid='" + sid + '\'' +
                '}';
    }
}

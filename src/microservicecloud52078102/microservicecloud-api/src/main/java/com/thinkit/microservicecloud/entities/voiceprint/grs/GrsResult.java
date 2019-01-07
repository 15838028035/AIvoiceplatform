package com.thinkit.microservicecloud.entities.voiceprint.grs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GrsResult {
    private String sid;
    private int ret;
    private String result;
    private int recStatus;

    @Override
    public String toString() {
        return "GrsResult{" +
                "sid='" + sid + '\'' +
                ", ret=" + ret +
                ", result='" + result + '\'' +
                ", recStatus=" + recStatus +
                '}';
    }
}

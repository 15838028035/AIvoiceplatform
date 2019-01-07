package com.thinkit.microservicecloud.entities.voiceprint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoiceResult {

    private String status;

    private String message;


    @Override
    public String toString() {
        return "VoiceResult{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

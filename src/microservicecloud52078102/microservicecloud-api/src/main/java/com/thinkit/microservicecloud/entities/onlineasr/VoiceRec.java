package com.thinkit.microservicecloud.entities.onlineasr;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoiceRec {

    private String  voicepath;
    private String  username;

    @Override
    public String toString() {
        return "VoiceRec{" +
                "voicepath='" + voicepath + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}

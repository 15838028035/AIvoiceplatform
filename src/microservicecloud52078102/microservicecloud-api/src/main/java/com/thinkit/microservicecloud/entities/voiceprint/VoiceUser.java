package com.thinkit.microservicecloud.entities.voiceprint;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class VoiceUser implements Serializable {

    private String voicepath;
    private String username;

    /**
     * type 0 1 2 3  注册 确认 识别 删除
     */
    private String type;

    @Override
    public String toString() {
        return "VoiceUser{" +
                "voicepath='" + voicepath + '\'' +
                ", username='" + username + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}


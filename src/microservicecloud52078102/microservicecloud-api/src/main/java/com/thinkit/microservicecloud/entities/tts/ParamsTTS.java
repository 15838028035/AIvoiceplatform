package com.thinkit.microservicecloud.entities.tts;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParamsTTS {

    private String text;
    private String lang;
    private  String speaker;
    private String speed;
    private String volume;

    @Override
    public String toString() {
        return "ParamsTTS{" +
                "text='" + text + '\'' +
                ", lang='" + lang + '\'' +
                ", speaker='" + speaker + '\'' +
                ", speed='" + speed + '\'' +
                ", volume='" + volume + '\'' +
                '}';
    }
}

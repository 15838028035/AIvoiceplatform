package com.thinkit.microservicecloud.entities.onlineasr;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@Data
public class VoiceUser implements Serializable {

    private String voicepath;
    private String username;




}


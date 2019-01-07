package com.thinkit.microservicecloud.entities.voiceprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseParams {
    private String jsonrpc;
    private int id;
    private SsbResult result;

    @Override
    public String toString() {
        return "ResponseParams{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id=" + id +
                ", result=" + result +
                '}';
    }
}

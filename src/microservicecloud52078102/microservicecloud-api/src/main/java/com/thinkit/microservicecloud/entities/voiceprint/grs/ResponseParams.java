package com.thinkit.microservicecloud.entities.voiceprint.grs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseParams {
    private String jsonrpc;
    private int id;
    private GrsResult result;

    @Override
    public String toString() {
        return "ResponseParams{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", id=" + id +
                ", result=" + result +
                '}';
    }
}

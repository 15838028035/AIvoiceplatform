package com.thinkit.microservicecloud.entities.voiceprint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestEntity {
    private String jsonrpc;
    private String method;
    private int id;
    private Param params;

    @Override
    public String toString() {
        return "RequestEntity{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", method='" + method + '\'' +
                ", id=" + id +
                ", params=" + params +
                '}';
    }
}

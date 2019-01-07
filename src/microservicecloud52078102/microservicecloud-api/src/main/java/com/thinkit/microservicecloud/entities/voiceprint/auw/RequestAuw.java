package com.thinkit.microservicecloud.entities.voiceprint.auw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAuw {
    private String jsonrpc;
    private String method;
    private int id;
    private Param params;

    @Override
    public String toString() {
        return "RequestAuw{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", method='" + method + '\'' +
                ", id=" + id +
                ", params=" + params +
                '}';
    }
}

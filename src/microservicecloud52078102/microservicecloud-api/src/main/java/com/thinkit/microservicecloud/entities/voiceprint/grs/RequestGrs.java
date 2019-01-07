package com.thinkit.microservicecloud.entities.voiceprint.grs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestGrs {
    private String jsonrpc;
    private String method;
    private int id;
    private Param params;

    @Override
    public String toString() {
        return "RequestGrs{" +
                "jsonrpc='" + jsonrpc + '\'' +
                ", method='" + method + '\'' +
                ", id=" + id +
                ", params=" + params +
                '}';
    }
}

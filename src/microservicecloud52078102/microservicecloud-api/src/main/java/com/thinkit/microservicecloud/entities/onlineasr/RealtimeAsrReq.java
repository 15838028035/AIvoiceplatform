package com.thinkit.microservicecloud.entities.onlineasr;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RealtimeAsrReq {
    private String id;
    private String number;
    private String newPath;
    private String oldPath;
    private String type;

    @Override
    public String toString() {
        return "RealtimeAsrReq{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", newPath='" + newPath + '\'' +
                ", oldPath='" + oldPath + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

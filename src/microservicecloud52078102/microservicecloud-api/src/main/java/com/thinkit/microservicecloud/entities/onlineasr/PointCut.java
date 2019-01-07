package com.thinkit.microservicecloud.entities.onlineasr;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PointCut {

    private String data;
    private String steData;
    private String startIndex;
    private String endIndex;

    @Override
    public String toString() {
        return "PointCut{" +
                "data='" + data + '\'' +
                ", steData='" + steData + '\'' +
                ", startIndex='" + startIndex + '\'' +
                ", endIndex='" + endIndex + '\'' +
                '}';
    }
}

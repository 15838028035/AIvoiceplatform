package com.thinkit.microservicecloud.entities.console;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnType {
    private String status;
    private String message;

    @Override
    public String toString() {
        return "ReturnType{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

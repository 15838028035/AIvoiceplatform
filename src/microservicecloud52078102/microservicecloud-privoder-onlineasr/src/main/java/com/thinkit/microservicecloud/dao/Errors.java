package com.thinkit.microservicecloud.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Errors {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Errors{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

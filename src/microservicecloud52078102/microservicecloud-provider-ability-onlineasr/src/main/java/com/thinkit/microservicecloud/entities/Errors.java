package com.thinkit.microservicecloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//根元素
@XmlRootElement(name = "errors")
//访问类型，通过字段
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Errors {
    private String code;
    private String description;

    @Override
    public String toString() {
        return "Errors{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

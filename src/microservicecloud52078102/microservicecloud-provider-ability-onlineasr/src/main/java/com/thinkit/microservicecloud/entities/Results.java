package com.thinkit.microservicecloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

//根元素
@XmlRootElement(name = "results")
//访问类型，通过字段
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Results {

    @XmlElement(name = "errors")
    private Errors errors;

    @XmlElement(name = "result")
    private Result result;

    @Override
    public String toString() {
        return "Results{" +
                "errors=" + errors +
                ", result=" + result +
                '}';
    }
}

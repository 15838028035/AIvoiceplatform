package com.thinkit.microservicecloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

//根元素
@XmlRootElement(name = "result")
//访问类型，通过字段
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private String no;
    private String name;
    private String confidence;
    private String phoneme;
    private String time;

    @Override
    public String toString() {
        return "Result{" +
                "no='" + no + '\'' +
                ", name='" + name + '\'' +
                ", confidence='" + confidence + '\'' +
                ", phoneme='" + phoneme + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

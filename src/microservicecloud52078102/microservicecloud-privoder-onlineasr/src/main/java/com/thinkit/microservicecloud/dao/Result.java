package com.thinkit.microservicecloud.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Result {
    private String no;
    private String name;
    private String confidence;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getPhoneme() {
        return phoneme;
    }

    public void setPhoneme(String phoneme) {
        this.phoneme = phoneme;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

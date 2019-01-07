package com.thinkit.microservicecloud.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Results {
    private  Errors errors ;
    private  Result result;


    public Errors getErrors() {
        return errors;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    @Override

    public String toString() {
        return "Results{" +
                "errors=" + errors +
                ", result=" + result +
                '}';
    }
}

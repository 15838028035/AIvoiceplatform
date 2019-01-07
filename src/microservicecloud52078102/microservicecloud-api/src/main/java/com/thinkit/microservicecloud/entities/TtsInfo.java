package com.thinkit.microservicecloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class TtsInfo implements Serializable {

    private  long id;
    private  String text;
    private  Date datetime;



}

package com.thinkit.microservicecloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenJson {
    private int auth_thread;
    private int access_thread;
    private List<String> access_sids;

    @Override
    public String toString() {
        return "TokenJson{" +
                "auth_thread=" + auth_thread +
                ", access_thread=" + access_thread +
                ", access_sids=" + access_sids +
                '}';
    }
}

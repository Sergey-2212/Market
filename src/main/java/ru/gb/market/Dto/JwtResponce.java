package ru.gb.market.Dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponce {

    private String token;

    public JwtResponce(String token) {
        this.token = token;
    }
}

package com.library.library.web.payloads;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }


    @Override
    public String toString() {
        return "{ \n" + accessToken + " \n" + tokenType + "\n}";
    }
}

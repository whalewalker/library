package com.library.library.web.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
    private String token;
    private String userId;
}

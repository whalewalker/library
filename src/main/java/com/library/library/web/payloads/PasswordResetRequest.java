package com.library.library.web.payloads;

import lombok.Data;

@Data
public class PasswordResetRequest {
    private String username;
    private String newPassword;
}

package com.library.library.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDto {
    @NotNull
    private String usernameOrEmail;

    @NotNull
    private String password;
}

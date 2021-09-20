package com.library.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data

public class AuthorDto {
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    private String password;

    public AuthorDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

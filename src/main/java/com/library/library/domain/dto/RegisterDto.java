package com.library.library.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterDto {
    @NotBlank
    @Size(min = 4, max = 40)
    private String name;

    @NotBlank
    @Size
    private String username;

    @NotBlank
    @Size(max=40)
    @Email
    private String email;

    @NotBlank
    @Size(min=6, max=20)
    private String password;

}

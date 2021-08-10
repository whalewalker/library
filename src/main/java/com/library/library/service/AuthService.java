package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.dto.RegisterDto;
import com.library.library.web.exceptions.AuthUserException;

public interface AuthService {
    RegisterDto register(RegisterDto registerDto) throws AuthUserException;
    String login(LoginDto loginDto);
    void forgetPassword(String email);
    void resetPassword(String password, String confirmPassword);
}

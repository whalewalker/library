package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.dto.RegisterDto;

public interface AuthService {
    void register(RegisterDto registerDto);
    void login(LoginDto loginDto);
    void forgetPassword(String email);
    void resetPassword(String password, String confirmPassword);
}

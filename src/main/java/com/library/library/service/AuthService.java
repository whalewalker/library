package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.dto.RegisterDto;
import com.library.library.web.exceptions.AuthUserException;
import com.library.library.web.exceptions.TokenException;
import com.library.library.web.payloads.AuthToken;
import com.library.library.web.payloads.PasswordResetRequest;
import com.library.library.web.payloads.Token;

public interface AuthService {
    RegisterDto register(RegisterDto registerDto) throws AuthUserException;
    AuthToken login(LoginDto loginDto) throws AuthUserException;
    Token generatePasswordResetToken(String username) throws AuthUserException;
    void resetUserPassword(PasswordResetRequest request, String token) throws AuthUserException, TokenException;
}

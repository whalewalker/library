package com.library.library.web.controllers;

import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.dto.RegisterDto;
import com.library.library.domain.models.Role;
import com.library.library.domain.repository.RoleRepository;
import com.library.library.service.AuthService;
import com.library.library.web.exceptions.AuthUserException;
import com.library.library.web.exceptions.TokenException;
import com.library.library.web.payloads.ApiResponse;
import com.library.library.web.payloads.AuthToken;
import com.library.library.web.payloads.PasswordResetRequest;
import com.library.library.web.payloads.Token;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.library.library.domain.models.RoleName.ADMIN;
import static com.library.library.domain.models.RoleName.USER;

@RestController
@Slf4j
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    RoleRepository roleRepository;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDto registerDto){
        try {
            RegisterDto createdAuthor = authService.register(registerDto);
            return new ResponseEntity<>(createdAuthor, HttpStatus.OK);
        } catch (AuthUserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        AuthToken authToken = null;
        try {
            authToken = authService.login(loginDto);
            return new ResponseEntity<>(authToken, HttpStatus.OK);
        } catch (AuthUserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/password/reset/{username}")
    public ResponseEntity<?> forgotPassword(@PathVariable String username){
        try {
            Token passwordResetToken = authService.generatePasswordResetToken(username);
            return new ResponseEntity<>(passwordResetToken, HttpStatus.OK);
        }catch (AuthUserException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/password/reset/{token}")
    public ResponseEntity<?> resetPassword(@PathVariable String token, @Valid  @RequestBody PasswordResetRequest request){
        try{
            authService.resetUserPassword(request, token);
            return new ResponseEntity<>(new ApiResponse(true, "Password reset is successful"), HttpStatus.OK);
        }catch (AuthUserException | TokenException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUser(@Param("token") String token){
        try {
           authService.verify(token);
                return new ResponseEntity<>(new ApiResponse(true, "User is verified"), HttpStatus.OK);

        } catch (AuthUserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }






}

package com.library.library.web.controllers;

import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.dto.RegisterDto;
import com.library.library.domain.models.Role;
import com.library.library.domain.repository.RoleRepository;
import com.library.library.service.AuthService;
import com.library.library.web.exceptions.AuthUserException;
import com.library.library.web.payloads.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>(registerDto, HttpStatus.OK);
        } catch (AuthUserException e) {
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto){
        log.info("dto {}", loginDto);
        String jwtToken = authService.login(loginDto);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }


}

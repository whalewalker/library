package com.library.library.service;

import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.dto.RegisterDto;
import com.library.library.domain.models.Author;
import com.library.library.domain.models.Role;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.domain.repository.RoleRepository;
import com.library.library.domain.repository.TokenRepository;
import com.library.library.web.exceptions.AppException;
import com.library.library.web.exceptions.AuthUserException;
import com.library.library.web.exceptions.TokenException;
import com.library.library.web.payloads.AuthToken;
import com.library.library.web.payloads.PasswordResetRequest;
import com.library.library.web.payloads.Token;
import com.library.library.web.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.library.library.domain.models.RoleName.ADMIN;
import static com.library.library.domain.models.RoleName.USER;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TokenRepository tokenRepository;


    @PostConstruct
    void setUp(){
        Role role = new Role();
        role.setId("001");
        role.setRoleName(USER);
        Role role1 = new Role();
        role1.setId("002");
        role1.setRoleName(ADMIN);
        List<Role> roleList = new ArrayList<>();
        roleList.add(role);
        roleList.add(role1);
        log.info("Saving roles ....");
        roleRepository.saveAll(roleList);
    }


    @Override
    public RegisterDto register(RegisterDto registerDto) throws AuthUserException {
       if (authorRepository.existsByUsername(registerDto.getUsername())){
           throw new AuthUserException("Username is already taken");
       }
       if (authorRepository.existsByEmail(registerDto.getEmail())){
           throw new AuthUserException("Email is already in use");
       }

        Author author = new Author(registerDto.getName(), registerDto.getUsername(), registerDto.getEmail(), passwordEncoder.encode(registerDto.getPassword()));
        Role userRole = roleRepository.findByRoleName(USER).orElseThrow(() -> new AppException("Author role is not set"));
        author.getRoles().add(userRole);

        Author createdAuthor = authorRepository.save(author);
        return modelMapper.map(author, RegisterDto.class);
    }

    @Override
    public AuthToken login(LoginDto loginDto) throws AuthUserException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        Author author = authorRepository.findByUsername(loginDto.getUsernameOrEmail()).orElseThrow(()-> new AuthUserException("User with this username does not exist!"));
        return new AuthToken(jwt, author.getId());
    }

    @Override
    public Token generatePasswordResetToken(String username) throws AuthUserException {
        Author author = authorRepository.findByUsername(username).orElseThrow(()-> new AuthUserException(String.format("User with %s username does not exist!", username)));
        Token token = new Token();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(author);
        token.setExpiryTimeStamp(LocalDateTime.now().plusMinutes(30));
        return tokenRepository.save(token);
    }

    @Override
    public void resetUserPassword(PasswordResetRequest request, String resetToken) throws AuthUserException, TokenException {
        String username = request.getUsername();
        String newPassword = request.getNewPassword();

        Author authorToResetPassword = authorRepository.findByUsername(username).orElseThrow(() -> new AuthUserException(String.format("User with %s does not exist", username)));
        Token token = tokenRepository.findByToken(resetToken).orElseThrow(()-> new TokenException(String.format("No token with value %s found", resetToken)));

        if(token.getExpiryTimeStamp().isBefore(LocalDateTime.now())) throw new TokenException("This password reset token has expired");
        if (!token.getUser().getId().equals(authorToResetPassword.getId())) throw new TokenException("password token does not belong to this user");

        authorToResetPassword.setPassword(passwordEncoder.encode(newPassword));
        authorRepository.save(authorToResetPassword);
        tokenRepository.delete(token);
    }
}

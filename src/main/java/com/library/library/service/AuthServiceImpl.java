package com.library.library.service;

import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.dto.RegisterDto;
import com.library.library.domain.models.Author;
import com.library.library.domain.models.Role;
import com.library.library.domain.models.RoleName;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.domain.repository.RoleRepository;
import com.library.library.web.exceptions.AppException;
import com.library.library.web.exceptions.AuthUserException;
import com.library.library.web.payloads.JwtAuthenticationResponse;
import com.library.library.web.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static com.library.library.domain.models.RoleName.*;

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
        log.info("User to be saved --> {}", author);
        Role userRole = roleRepository.findByRoleName(USER).orElseThrow(() -> new AppException("Author role is not set"));
        author.getRoles().add(userRole);

        Author createdAuthor = authorRepository.save(author);
        log.info("Saved user details --> {}", createdAuthor);
        return modelMapper.map(author, RegisterDto.class);
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return new JwtAuthenticationResponse(jwt).getAccessToken();
    }

    @Override
    public void forgetPassword(String email) {

    }

    @Override
    public void resetPassword(String password, String confirmPassword) {

    }
}

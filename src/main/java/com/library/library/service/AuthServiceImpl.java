package com.library.library.service;

import com.library.library.domain.dto.AuthorDto;
import com.library.library.domain.dto.LoginDto;
import com.library.library.domain.models.Author;
import com.library.library.domain.models.Role;
import com.library.library.domain.repository.AuthorRepository;
import com.library.library.domain.repository.TokenRepository;
import com.library.library.web.exceptions.AuthUserException;
import com.library.library.web.exceptions.TokenException;
import com.library.library.web.payloads.AuthToken;
import com.library.library.web.payloads.PasswordResetRequest;
import com.library.library.web.payloads.Token;
import com.library.library.web.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper = new ModelMapper();


    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private MailService mailService;

    @Autowired
    public AuthServiceImpl(AuthorRepository authorRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = passwordEncoder;
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }


    @Override
    public Author registerUser(AuthorDto registerDto) throws AuthUserException {
        if (existByUsername(registerDto.getUsername())) {
            throw new AuthUserException("Username is already taken");
        }
        if (existByEmail(registerDto.getEmail())) {
            throw new AuthUserException("Email is already in use");
        }
        Author newAuthor = modelMapper.map(registerDto, Author.class);
        log.info("User to save ==> {}", newAuthor);
        newAuthor.getRoles().add(Role.USER);
        newAuthor.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newAuthor.setVerificationToken(UUID.randomUUID().toString());
        Author savedAuthor = authorRepository.save(newAuthor);

//        Mail mail = new Mail();
//        String link = "http://localhost:8080/api/auth/verify?token=";
//        mail.setMailTo(savedAuthor.getEmail());
//        mail.setMailSubject("Mail Confirmation Link!");
//        mail.setMailContent("Thank you for registering. Please click on the below link to activate your account." + link + savedAuthor.getVerificationToken());
//
//        mailService.sendMail(mail);
        return savedAuthor;
    }

    private boolean existByEmail(String email) {
        return authorRepository.existsByEmail(email);
    }

    private boolean existByUsername(String username) {
        return authorRepository.existsByUsername(username);
    }

    public boolean verify(String verificationToken) throws AuthUserException {
        Author author = authorRepository.findByVerificationToken(verificationToken).orElseThrow(() -> new AuthUserException("No user with this verification token"));
        author.setActive(true);
        authorRepository.save(author);
        return true;
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
        Author author = authorRepository.findByUsername(loginDto.getUsernameOrEmail()).orElseThrow(() -> new AuthUserException("User with this username does not exist!"));
        return new AuthToken(jwt, author.getId());
    }

    @Override
    public Token generatePasswordResetToken(String username) throws AuthUserException {
        Author author = authorRepository.findByUsername(username).orElseThrow(() -> new AuthUserException(String.format("User with %s username does not exist!", username)));
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
        Token token = tokenRepository.findByToken(resetToken).orElseThrow(() -> new TokenException(String.format("No token with value %s found", resetToken)));

        if(token.getExpiryTimeStamp().isBefore(LocalDateTime.now())) throw new TokenException("This password reset token has expired");
        if (!token.getUser().getId().equals(authorToResetPassword.getId())) throw new TokenException("password token does not belong to this user");

        authorToResetPassword.setPassword(passwordEncoder.encode(newPassword));
        authorRepository.save(authorToResetPassword);
        tokenRepository.delete(token);
    }
}

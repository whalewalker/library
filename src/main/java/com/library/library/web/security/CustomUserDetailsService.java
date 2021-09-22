package com.library.library.web.security;

import com.library.library.domain.models.Author;;
import com.library.library.domain.repository.AuthorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    AuthorRepository authorRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        log.info(usernameOrEmail);
        log.info("load method called ...");
        Optional<Author> author = authorRepository.findByUsername(usernameOrEmail);
        log.info("username empty ==> {}", author);
        if(author.isEmpty()) {
            log.info("username empty ==> {}", author);
            author = authorRepository.findByEmail(usernameOrEmail);
            log.info("username empty find by email ==> {}", author);
        }
        if(author.isPresent()){
            log.info("email valid...");
            return UserPrincipal.create(author.get());
        } else {
            log.info("no match");
            throw new UsernameNotFoundException("User not found with username or email " + usernameOrEmail);
        }

    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(String id){
        Author author = authorRepository.findById(id).orElseThrow(()->
            new UsernameNotFoundException("Author not found with id: " + id)
        );
        return UserPrincipal.create(author);
    }
}

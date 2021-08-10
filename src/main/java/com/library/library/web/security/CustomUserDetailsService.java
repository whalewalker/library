package com.library.library.web.security;

import com.library.library.domain.models.Author;
import com.library.library.domain.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    AuthorRepository authorRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Let people login with either username or email
        Author author = authorRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(() -> new UsernameNotFoundException("Author not found with username or email " + usernameOrEmail));
        return UserPrincipal.create(author);
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

package com.library.library.web.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.library.domain.models.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
public class UserPrincipal implements UserDetails {

    private String id;
    private String name;
    private String username;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(Author author)  {
        List<GrantedAuthority> authorities = author.getRoles().stream().map(role ->
                        new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());

        return new UserPrincipal(
                author.getId(),
                author.getFirstName(), author.getUsername(),
                author.getEmail(), author.getPassword(),
                authorities);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof UserPrincipal)) return false;
        UserPrincipal comparedObject = (UserPrincipal) obj;
        return Objects.equals(getId(), comparedObject.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

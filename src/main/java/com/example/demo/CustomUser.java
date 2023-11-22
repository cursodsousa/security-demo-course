package com.example.demo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CustomUser implements UserDetails {

    private String password;
    private String username;
    private List<String> authoritiesList;

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

    public List<GrantedAuthority> getAuthorities() {
        return authoritiesList.stream().map(a -> new SimpleGrantedAuthority(a)).collect(Collectors.toList());
    }
}

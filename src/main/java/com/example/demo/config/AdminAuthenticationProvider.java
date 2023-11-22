package com.example.demo.config;

import com.example.demo.CustomAuthentication;
import com.example.demo.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AdminAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = authentication.getCredentials().toString();

        if(!password.equals("admin123")){
            throw new AuthenticationException("Invalid Credentials!") {};
        }
        CustomUser user = new CustomUser();
        user.setAuthoritiesList(Arrays.asList("ADMIN"));
        return new CustomAuthentication(user);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

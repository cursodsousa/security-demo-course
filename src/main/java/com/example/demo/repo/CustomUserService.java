package com.example.demo.repo;


import com.example.demo.CustomUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserService implements UserDetailsService {

    private static List<CustomUser> users;

    public void add(CustomUser user){
        getUsers().add(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public static List<CustomUser> getUsers() {
        if(users == null){
            users = new ArrayList<>();
        }
        return users;
    }
}

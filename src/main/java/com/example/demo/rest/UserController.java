package com.example.demo.rest;

import com.example.demo.CustomAuthentication;
import com.example.demo.CustomUser;
import com.example.demo.config.annotations.IsAdmin;
import com.example.demo.repo.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UserController {

    private final CustomUserService customUserService;
    private final PasswordEncoder encoder;

    @GetMapping("/public")
    String publicEndpoint(){
        return "public OK";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('USER')")
    String privateEndpoint(){
        return "private OK";
    }

    @GetMapping("/admin")
    @IsAdmin
    String privateUnsecEndpoint(){
        return "ADMIN OK";
    }

    @PostMapping("/users")
    ResponseEntity<CustomUser> save(@RequestBody CustomUser user){
        String encodedPass = encoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        customUserService.add(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/logged-user")
    @IsAdmin
    ResponseEntity<CustomUser> getLoggedUser(Authentication authentication){
        var customAuth = (CustomAuthentication) authentication;
        return ResponseEntity.ok(customAuth.getUser());
    }
}

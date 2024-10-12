package com.documentmanager.controller;

import com.documentmanager.config.UserDetailsImpl;
import com.documentmanager.config.UserDetailsServiceImpl;
import com.documentmanager.service.UserService;
import com.documentmanager.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.documentmanager.model.User;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        userService.save(user);
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtil.GenerateToken(userDetails);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }
}

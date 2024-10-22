package com.documentmanager.controller;

import com.documentmanager.dto.JwtRequest;
import com.documentmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createToken(@RequestBody JwtRequest jwtRequest) {
        return new ResponseEntity<>(userService.login(jwtRequest), HttpStatus.OK);
    }
}

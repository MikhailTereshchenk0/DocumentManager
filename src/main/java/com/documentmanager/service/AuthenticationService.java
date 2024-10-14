package com.documentmanager.service;

import com.documentmanager.config.UserDetailsImpl;
import com.documentmanager.config.UserDetailsServiceImpl;
import com.documentmanager.dto.JwtRequest;
import com.documentmanager.dto.JwtResponse;
import com.documentmanager.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public JwtResponse login(JwtRequest jwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String accessToken = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(accessToken);
    }
}

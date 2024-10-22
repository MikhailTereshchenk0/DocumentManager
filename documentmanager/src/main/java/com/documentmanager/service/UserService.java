package com.documentmanager.service;

import com.documentmanager.config.UserDetailsImpl;
import com.documentmanager.config.UserDetailsServiceImpl;
import com.documentmanager.dto.JwtRequest;
import com.documentmanager.dto.JwtResponse;
import com.documentmanager.exception.BadRequestException;
import com.documentmanager.exception.NotFoundException;
import com.documentmanager.model.User;
import com.documentmanager.repository.IUserRepository;
import com.documentmanager.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public Optional<User> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new NotFoundException("User Not Found.");
        return user;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new BadRequestException("User " + user.getUsername() + " Already Exists.");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User Not Found.");
        }
        userRepository.deleteById(id);
    }

    public JwtResponse login(JwtRequest jwtRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String accessToken = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(accessToken);
    }

    public JwtResponse register(User user) {
        this.save(user);
        UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(user.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return new JwtResponse(token);
    }
}

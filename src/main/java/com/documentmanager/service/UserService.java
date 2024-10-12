package com.documentmanager.service;

import com.documentmanager.exception.BadRequestException;
import com.documentmanager.exception.NotFoundException;
import com.documentmanager.model.User;
import com.documentmanager.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
}

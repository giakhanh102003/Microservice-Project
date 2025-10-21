package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.RegisterRequestDTO;
import org.example.authservice.dto.request.RequestLoginDTO;
import org.example.authservice.dto.response.LoginResponse;
import org.example.authservice.entity.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Transactional
    public void register(RegisterRequestDTO request) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("Email đã tồn tại trong hệ thống");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
        user.setRole(request.getRole().toUpperCase());
        user.setCreatedAt(new Date());
        userRepository.save(user);

    }

    @Override
    public LoginResponse login(RequestLoginDTO request) throws Exception {
        return null;
    }
}

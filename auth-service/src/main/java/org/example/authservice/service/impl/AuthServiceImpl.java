package org.example.authservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.authservice.dto.request.RegisterRequestDTO;
import org.example.authservice.dto.response.UserResponse;
import org.example.authservice.entity.User;
import org.example.authservice.repository.UserRepository;
import org.example.authservice.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<UserResponse> getUsers() throws Exception {
        // 1. Lấy tất cả User entity từ database
        List<User> users = userRepository.findAll();

        // 2. Chuyển đổi (map) danh sách User entity sang danh sách UserResponse DTO
        return users.stream()
                .map(user -> UserResponse.builder()
                        .id(user.getId()) // Giả sử User entity có trường id
                        .fullName(user.getFullName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkUserExists(Integer userId) throws Exception {
        return userRepository.existsById(userId);
    }


}

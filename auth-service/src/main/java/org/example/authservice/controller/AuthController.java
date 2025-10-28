package org.example.authservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.authservice.dto.ResponseDTO;
import org.example.authservice.dto.request.RegisterRequestDTO;
import org.example.authservice.dto.request.RequestLoginDTO;
import org.example.authservice.dto.response.LoginResponse;
import org.example.authservice.dto.response.UserResponse;
import org.example.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final ObjectMapper objectMapper;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) throws Exception {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users")
    public ResponseEntity<?> getUsers() throws Exception{
        List<UserResponse> users = authService.getUsers();
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(users))
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
    @GetMapping("/users/{id}/exists")
    public ResponseEntity<?> checkUserExists(@PathVariable("id") Integer userId) throws Exception {
        boolean exists = authService.checkUserExists(userId); // implement trong AuthService
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(exists))
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestLoginDTO request) throws Exception {
        LoginResponse loginResponse = authService.login(request);
        ResponseDTO responseDTO = ResponseDTO.builder()
                .data(objectMapper.valueToTree(loginResponse))
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }
}

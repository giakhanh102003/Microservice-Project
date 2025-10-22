package org.example.authservice.service;

import org.example.authservice.dto.request.RegisterRequestDTO;
import org.example.authservice.dto.response.UserResponse;

import java.util.List;

public interface AuthService {
    void register(RegisterRequestDTO request) throws Exception;

    List<UserResponse> getUsers() throws Exception;
}

package org.example.authservice.service;

import org.example.authservice.dto.request.RegisterRequestDTO;
import org.example.authservice.dto.request.RequestLoginDTO;
import org.example.authservice.dto.response.LoginResponse;

public interface AuthService {
    void register(RegisterRequestDTO request) throws Exception;

    public LoginResponse login(RequestLoginDTO request) throws Exception;
}

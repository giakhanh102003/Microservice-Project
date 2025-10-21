package org.example.authservice.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String email;
    private String password;
    private String fullName;
    private String role;
}
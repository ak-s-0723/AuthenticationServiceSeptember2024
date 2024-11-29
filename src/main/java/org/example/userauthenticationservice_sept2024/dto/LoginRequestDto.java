package org.example.userauthenticationservice_sept2024.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginRequestDto implements Serializable {
    Long id;
    private String email;
    private String password;
    private String role;
}

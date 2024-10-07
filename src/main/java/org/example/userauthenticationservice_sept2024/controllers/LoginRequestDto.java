package org.example.userauthenticationservice_sept2024.controllers;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.userauthenticationservice_sept2024.models.User}
 */
@Data
public class LoginRequestDto implements Serializable {
    Long id;
    String email;
    String password;
    String role;
}
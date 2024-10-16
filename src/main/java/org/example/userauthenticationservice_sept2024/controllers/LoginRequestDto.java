package org.example.userauthenticationservice_sept2024.controllers;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.userauthenticationservice_sept2024.models.User}
 */
@Setter
@Getter
public class LoginRequestDto implements Serializable {
    Long id;
    String email;
    String password;
    String role;
}
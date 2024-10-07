package org.example.userauthenticationservice_sept2024.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservice_sept2024.models.UserAuthenticationStatus;

@Getter
@Setter
public class LoginResponseDTO {
    private String authToken;
    private UserAuthenticationStatus userAuthenticationStatus;
}
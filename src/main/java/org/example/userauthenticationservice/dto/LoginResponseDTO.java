package org.example.userauthenticationservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservice.models.UserAuthenticationStatus;

@Getter
@Setter
public class LoginResponseDTO {
    private String authToken;
    private UserAuthenticationStatus userAuthenticationStatus;
}

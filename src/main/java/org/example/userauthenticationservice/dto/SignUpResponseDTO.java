package org.example.userauthenticationservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservice.models.UserAuthenticationStatus;

@Getter
@Setter
public class SignUpResponseDTO {
    private UserAuthenticationStatus status;
}

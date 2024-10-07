package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservice_sept2024.models.User;

@Getter
@Setter
public class LoginResponseDto {
    RequestStatus requestStatus;
    String token;
}

package org.example.userauthenticationservice_sept2024.dto;

import lombok.Data;
import org.example.userauthenticationservice_sept2024.config.RequestStatus;

@Data
public class LoginResponseDto {
    private String email;
    private String password;
    private RequestStatus requestStatus;
}

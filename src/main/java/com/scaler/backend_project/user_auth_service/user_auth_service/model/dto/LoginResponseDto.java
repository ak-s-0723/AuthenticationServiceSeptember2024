package com.scaler.backend_project.user_auth_service.user_auth_service.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto {
    private RequestStatus status;
    private String token;
}

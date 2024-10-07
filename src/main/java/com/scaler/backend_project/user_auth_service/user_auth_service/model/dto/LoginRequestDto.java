package com.scaler.backend_project.user_auth_service.user_auth_service.model.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String email;
    private String password;
    private String status;

}

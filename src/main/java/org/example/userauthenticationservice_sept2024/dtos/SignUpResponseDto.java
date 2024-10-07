package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Data;

@Data
public class SignUpResponseDto {
    private String email;
    private String password;
    private RequestStatus requestStatus;

}

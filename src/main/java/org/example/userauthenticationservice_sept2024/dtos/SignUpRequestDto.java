package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Data;

@Data
public class SignUpRequestDto {
    private String email;
    private String password;
//    private string userRole;
}

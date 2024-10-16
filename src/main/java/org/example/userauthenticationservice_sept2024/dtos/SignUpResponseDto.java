package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpResponseDto {
    private String email;
    private String password;
    private RequestStatus requestStatus;

}

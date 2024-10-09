package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SignUpRequestDto {

    private String email;
    private String password;
}

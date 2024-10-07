package org.example.userauthenticationservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDTO {
    private String email;
    private String password;
}

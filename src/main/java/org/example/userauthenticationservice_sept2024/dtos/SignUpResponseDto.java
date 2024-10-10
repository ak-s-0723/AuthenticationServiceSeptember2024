package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Data;
import org.example.userauthenticationservice_sept2024.models.RequestStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class SignUpResponseDto {
    private String email;
    private String password;
    private RequestStatus status;
//    private String userRole;
}

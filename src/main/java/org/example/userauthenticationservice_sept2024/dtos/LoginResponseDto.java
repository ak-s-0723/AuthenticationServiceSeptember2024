package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.example.userauthenticationservice_sept2024.models.User}
 */
@Setter
@Getter
public class LoginResponseDto implements Serializable {
    RequestStatus requestStatus;
    String role;
    String token;
}
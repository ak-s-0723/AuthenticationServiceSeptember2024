package org.example.userauthenticationservice_sept2024.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.userauthenticationservice_sept2024.models.RequestStatus;

@Getter
@Setter
public class SignUpResponseDto {
    private RequestStatus requestStatus;
}

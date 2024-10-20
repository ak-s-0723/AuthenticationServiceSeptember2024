package org.example.userauthenticationservice_sept2024.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private RequestStatus requestStatus;
    private String message;
}

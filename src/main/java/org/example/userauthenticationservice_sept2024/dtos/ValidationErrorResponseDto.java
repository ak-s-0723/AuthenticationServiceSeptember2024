package org.example.userauthenticationservice_sept2024.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorResponseDto {
    private RequestStatus responseStatus;
    private String errorField;
    private String errorMessage;
    private String errorValue;
}

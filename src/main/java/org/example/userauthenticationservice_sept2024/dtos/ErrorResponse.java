package org.example.userauthenticationservice_sept2024.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.userauthenticationservice_sept2024.models.RequestStatus;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private RequestStatus requestStatus;
}

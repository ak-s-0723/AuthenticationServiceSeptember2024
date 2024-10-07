package org.example.userauthenticationservice_sept2024.dtos;

public class SignupResponseDto {
    private RequestStatus requestStatus;

    // Getters and Setters
    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
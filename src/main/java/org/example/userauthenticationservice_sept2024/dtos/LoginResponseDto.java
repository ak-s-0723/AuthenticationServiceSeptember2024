package org.example.userauthenticationservice_sept2024.dtos;

public class LoginResponseDto {
    RequestStatus requestStatus;

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}

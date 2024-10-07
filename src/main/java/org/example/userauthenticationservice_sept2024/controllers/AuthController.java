package org.example.userauthenticationservice_sept2024.controllers;

import org.example.userauthenticationservice_sept2024.dtos.*;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    public ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto request) {
        SignUpResponseDto response = new SignUpResponseDto();
        if(authService.signUp(request.getEmail(), request.getPassword())) {
            response.setStatus(ResponseStatus.SUCCESS);
        }else
            response.setStatus(ResponseStatus.FAILURE);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        return loginResponseDto;
    }
}

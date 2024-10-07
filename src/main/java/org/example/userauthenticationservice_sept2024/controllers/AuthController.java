package org.example.userauthenticationservice_sept2024.controllers;

import org.example.userauthenticationservice_sept2024.dtos.*;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<SignUpResponseDto> signUp(SignUpRequestDto requestDto) {
        SignUpResponseDto responseDto = new SignUpResponseDto();
        try {
            if (authService.signUp(requestDto.getEmail(), requestDto.getPassword())) {
                responseDto.setRequestStatus(RequestStatus.SUCCESS);
            } else {
                responseDto.setRequestStatus(RequestStatus.FAILURE);
            }

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
        }
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponseDto> login(LoginRequestDto requestDto) {
        LoginResponseDto responseDto = new LoginResponseDto();

        try {
            authService.login(requestDto.getEmail(), requestDto.getPassword());
            responseDto.setStatus(RequestStatus.SUCCESS);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        } catch (Exception ex) {
            responseDto.setStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
        }
    }
}

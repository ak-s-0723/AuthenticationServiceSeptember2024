package org.example.userauthenticationservice_sept2024.controllers;

import org.example.userauthenticationservice_sept2024.dtos.SignUpRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpResponseDto;
import org.example.userauthenticationservice_sept2024.models.RequestStatus;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private SignUpRequestDto request;
    private AuthService authService;
    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto response = new SignUpResponseDto();
        try{
            if(authService.signUp(request.getEmail(),request.getPassword())) {
                response.setRequestStatus(RequestStatus.SUCCESS);
            } else {
                response.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}

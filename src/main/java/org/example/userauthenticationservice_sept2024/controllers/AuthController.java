package org.example.userauthenticationservice_sept2024.controllers;

import org.example.userauthenticationservice_sept2024.dtos.SignUpRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpResponseDto;
import org.example.userauthenticationservice_sept2024.models.RequestStatus;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try {
            if(authService.signIn(signUpRequestDto.getEmail(),signUpRequestDto.getPassword())){
                signUpResponseDto.setRequestStatus(RequestStatus.SUCCESS);
            }else {
                signUpResponseDto.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

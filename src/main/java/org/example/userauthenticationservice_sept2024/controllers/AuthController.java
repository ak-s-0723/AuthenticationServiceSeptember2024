package org.example.userauthenticationservice_sept2024.controllers;


import org.example.userauthenticationservice_sept2024.dtos.SignUpRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpResponseDto;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    public ResponseEntity<SignUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto response = new SignUpResponseDto();

        try {
            if (authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword())) {
                //response.set
            }
        } catch (Exception e) {

        }


        return null;

    }
}

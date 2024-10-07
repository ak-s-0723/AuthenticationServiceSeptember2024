package org.example.userauthenticationservice_sept2024.controllers;


import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.userauthenticationservice_sept2024.dtos.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto request) {
        SignupResponseDto response = new SignupResponseDto();
        try {
            if(authService.signUp(request.getEmail(), request.getPassword())) {
               response.setRequestStatus(RequestStatus.SUCCESS);
            } else {
               response.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
           response.setRequestStatus(RequestStatus.FAILURE);
              return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto response = new LoginResponseDto();
        try {
            if(authService.login(request.getEmail(), request.getPassword())) {
               response.setRequestStatus(RequestStatus.SUCCESS);
            } else {
               response.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
           response.setRequestStatus(RequestStatus.FAILURE);
              return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

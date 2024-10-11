package org.example.userauthenticationservice_sept2024.controllers;

import jakarta.persistence.Entity;
import org.example.userauthenticationservice_sept2024.dtos.LoginRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.LoginResponseDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpResponseDto;
import org.example.userauthenticationservice_sept2024.models.RequestStatus;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto request){
        SignUpResponseDto response =  new SignUpResponseDto();
        try{
            if(authService.signUp(request.getEmail(),request.getPassword())){
                response.setRequestStatus(RequestStatus.SUCCESS);
            } else {
                response.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e){
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request){
        LoginResponseDto response =  new LoginResponseDto();;
        try{
            boolean token = authService.login(request.getEmail(), request.getPassword());
            //response.setToken(token);
            response.setRequestStatus(RequestStatus.SUCCESS);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(
                    response, HttpStatus.BAD_REQUEST);
        }
    }
}

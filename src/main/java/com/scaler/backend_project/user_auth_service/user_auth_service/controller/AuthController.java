package com.scaler.backend_project.user_auth_service.user_auth_service.controller;

import com.scaler.backend_project.user_auth_service.user_auth_service.exception.UserAlreadyExistException;
import com.scaler.backend_project.user_auth_service.user_auth_service.exception.UserNotFoundException;
import com.scaler.backend_project.user_auth_service.user_auth_service.exception.WrongPasswordException;
import com.scaler.backend_project.user_auth_service.user_auth_service.model.dto.*;
import com.scaler.backend_project.user_auth_service.user_auth_service.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    final private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signup")
    public ResponseEntity<SignupResponseDto> signUp(@RequestBody SignupRequestDto request) {
        SignupResponseDto response = new SignupResponseDto();
        try {
            authService.signUp(request.getEmail(), request.getPassword());
            response.setStatus(RequestStatus.SUCCESS);
            return ResponseEntity.ok(response);
        } catch (UserAlreadyExistException uae) {
            response.setStatus(RequestStatus.FAILURE);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto request) {
        LoginResponseDto response = new LoginResponseDto();
        try {
            String token = authService.login(request.getEmail(), request.getPassword());
            response.setToken(token);
            response.setStatus(RequestStatus.SUCCESS);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException|WrongPasswordException e) {
            response.setStatus(RequestStatus.FAILURE);
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

}

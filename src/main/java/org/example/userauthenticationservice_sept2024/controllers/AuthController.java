package org.example.userauthenticationservice_sept2024.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.userauthenticationservice_sept2024.dtos.LoginRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.LoginResponseDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpResponseDto;
import org.example.userauthenticationservice_sept2024.models.RequestStatus;
import org.example.userauthenticationservice_sept2024.services.AuthServiceImpl;
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

    private static final Logger log = LogManager.getLogger(AuthController.class);
    @Autowired
    private AuthServiceImpl authService;

    @PostMapping(value = "/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        try {
            if(authService.signUp(signUpRequestDto.getEmail(),signUpRequestDto.getPassword())){
                signUpResponseDto.setRequestStatus(RequestStatus.SUCCESS);
            }else {
                signUpResponseDto.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            signUpResponseDto.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(signUpResponseDto, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        try{
            String token = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            loginResponseDto.setToken(token);
            loginResponseDto.setStatus(RequestStatus.SUCCESS);
            return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            log.info(e.getMessage());
            loginResponseDto.setStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(loginResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}

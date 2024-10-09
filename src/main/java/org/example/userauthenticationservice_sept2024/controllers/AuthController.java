package org.example.userauthenticationservice_sept2024.controllers;


import org.example.userauthenticationservice_sept2024.dtos.LoginRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.LoginResponseDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpRequestDto;
import org.example.userauthenticationservice_sept2024.dtos.SignUpResponseDto;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {
        SignUpResponseDto response = new SignUpResponseDto();
        try{
            if(authService.signUp(requestDto.getEmail(), requestDto.getPassword())){
                response.setSuccess(Boolean.TRUE);
            }else{
                response.setSuccess(Boolean.FALSE);
            }
          return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = new LoginResponseDto();
        try{
            authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            response.setSuccess(Boolean.TRUE);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

}

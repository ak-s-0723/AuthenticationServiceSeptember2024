package org.example.userauthenticationservice_sept2024.controllers;


import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthenticationservice_sept2024.dtos.*;
import org.example.userauthenticationservice_sept2024.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto response = new SignUpResponseDto();
        System.out.println("Working line1");
        try{
            System.out.println("Working line2");
            if( authService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword()) ){
                response.setRequestStatus(RequestStatus.SUCCESS);
            }
            else{
                response.setRequestStatus(RequestStatus.FAILURE);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch(Exception e){
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = new LoginResponseDto();
        try{
//            String token = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
//            response.setToken(token);
//            response.setRequestStatus(RequestStatus.SUCCESS);
            Pair<Boolean,String> tokenWithResult = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            response.setRequestStatus(RequestStatus.SUCCESS);
            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.SET_COOKIE,tokenWithResult.b);
//            if(  authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword())){
//                response.setRequestStatus(RequestStatus.SUCCESS);
//            }
//            else{
//                response.setRequestStatus(RequestStatus.FAILURE);
//            }
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        }
        catch(Exception e){
            response.setRequestStatus(RequestStatus.FAILURE);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}

package org.example.userauthenticationservice_sept2024.controllers;

import org.example.userauthenticationservice_sept2024.dto.LoginRequestDTO;
import org.example.userauthenticationservice_sept2024.dto.LoginResponseDTO;
import org.example.userauthenticationservice_sept2024.dto.SignUpRequestDTO;
import org.example.userauthenticationservice_sept2024.dto.SignUpResponseDTO;
import org.example.userauthenticationservice_sept2024.models.UserAuthenticationStatus;
import org.example.userauthenticationservice_sept2024.services.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserAuthenticationController {
    private IAuthService userAuthenticationService;

    public UserAuthenticationController(IAuthService userAuthenticationService) {
        this.userAuthenticationService = userAuthenticationService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO responseDTO = new SignUpResponseDTO();

        try {
            if(userAuthenticationService.signUp(signUpRequestDTO.getEmail(), signUpRequestDTO.getPassword())) {
                responseDTO.setStatus(UserAuthenticationStatus.SUCCESS);
            } else {
                responseDTO.setStatus(UserAuthenticationStatus.FAILURE);
            }

            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            responseDTO.setStatus(UserAuthenticationStatus.FAILURE);
            return new ResponseEntity<>(responseDTO, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO responseDTO = new LoginResponseDTO();

        try{
            String authToken = userAuthenticationService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
            responseDTO.setUserAuthenticationStatus(UserAuthenticationStatus.SUCCESS);
            responseDTO.setAuthToken(authToken);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            responseDTO.setUserAuthenticationStatus(UserAuthenticationStatus.FAILURE);
            return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    }
}

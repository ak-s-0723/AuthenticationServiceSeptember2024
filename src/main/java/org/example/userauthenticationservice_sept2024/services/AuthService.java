package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean signUp(String email, String password) {
        if(this.userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyExistsException("User with email: " + email + " already exists!!!");
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User user = User.builder()
                .email(email)
                .password(encodedPassword)
                .build();
        userRepository.save(user);
        return true;
    }
    public String login(String email, String password) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found!!!"));
        if(bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return Arrays.toString(Base64.getEncoder().encode((user.getEmail() + ":" + user.getPassword()).getBytes(StandardCharsets.UTF_8)));
        }
        throw new WrongPasswordException("Wrong Password for the email: " + email);
    }
}

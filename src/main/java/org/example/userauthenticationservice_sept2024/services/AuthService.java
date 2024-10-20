package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    @Autowired
    AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean signUp(String email, String password) {
        if(this.userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyExistsException("User with email: " + email + " already exists!!!");
        User user = User.builder()
                .email(email)
                .password(password)
                .build();
        userRepository.save(user);
        return true;
    }
    public boolean login(String email, String password) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email: " + email + " not found!!!"));
        if(user.getPassword().equals(password))
            return true;

        throw new WrongPasswordException("Wrong Password for the email: " + email);
    }
}

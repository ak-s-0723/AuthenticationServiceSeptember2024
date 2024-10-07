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
    private UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean signUp(String email, String password) throws UserAlreadyExistsException,WrongPasswordException {
        if(userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException("User with email "+email+" already exists");
        }
        User user = new User();
        user.setEmail(email);
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }
    public String login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        var user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        var matches = passwordEncoder.matches(password, user.getPassword();
        if(matches) return email + ":" + password;
        else throw new WrongPasswordException("Wrong password");
    }
}

package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exception.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exception.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exception.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User with email: " + email + "already exists");
        }
        User user = new User();
        user.setEmail(email);
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return true;
    }

    public String login(String email, String password) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " not found");
        }
        User user = userOptional.get();
        if (passwordEncoder.matches(password, user.getPassword())) {
            return email + ":" + password;
        } else {
            throw new WrongPasswordException("Wrong password.");
        }
    }


}

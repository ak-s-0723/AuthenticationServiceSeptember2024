package com.scaler.backend_project.user_auth_service.user_auth_service.service;

import com.scaler.backend_project.user_auth_service.user_auth_service.exception.UserAlreadyExistException;
import com.scaler.backend_project.user_auth_service.user_auth_service.exception.UserNotFoundException;
import com.scaler.backend_project.user_auth_service.user_auth_service.exception.WrongPasswordException;
import com.scaler.backend_project.user_auth_service.user_auth_service.model.entity.User;
import com.scaler.backend_project.user_auth_service.user_auth_service.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    final private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email, String password) throws UserAlreadyExistException {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyExistException("User with email '" + email + "' already exists");

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return true;
    }

    public String login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty())
            throw new UserNotFoundException("User with email '" + email + "' not found");

        User user = optionalUser.get();
        if (!password.equals(user.getPassword()))
            throw new WrongPasswordException("Wrong password");

        return email + ":" + password;
    }
}

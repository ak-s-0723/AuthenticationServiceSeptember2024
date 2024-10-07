package org.example.userauthenticationservice_sept2024.service;

import com.amazonaws.services.elasticache.model.UserAlreadyExistsException;
import com.amazonaws.services.elasticache.model.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exception.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.model.User;
import org.example.userauthenticationservice_sept2024.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email, String password) throws UserAlreadyExistsException {

        if (userRepository.findByEmail(email).

                isPresent()) {
            throw new UserAlreadyExistsException("User with email: " + email + "already exist");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public String login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        var user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UserNotFoundException("User with "+email+" not exists");
        }
        var matches = password.equals(user.get().getPassword());
        if(matches) return email + ":" + password;
        else throw new WrongPasswordException("Wrong password");
    }
}
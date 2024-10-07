package org.example.userauthenticationservice_sept2024.services;


import org.example.userauthenticationservice_sept2024.UserAlreadyExistException;
import org.example.userauthenticationservice_sept2024.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email, String password) throws UserAlreadyExistException {

        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("User with email: "+email+ " already exist");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()) {
            throw new UserNotFoundException();
        }

        boolean matches = password.equals(userOptional.get().getPassword());

        if(matches) {
            return true;
        } else {
            throw new WrongPasswordException();
        }
    }
}

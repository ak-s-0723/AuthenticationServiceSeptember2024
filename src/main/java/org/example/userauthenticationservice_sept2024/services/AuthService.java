package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exception.UserAlreadyExistException;
import org.example.userauthenticationservice_sept2024.exception.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exception.WrongPasswordException;
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

    public boolean signUp(String email  , String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("User with email: " + email + " already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return true;

    }

    public boolean login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " not found"); }
        boolean matches = password.equals(userOptional.get().getPassword());
        if(matches){
            return true;
        }else{
            throw new WrongPasswordException("Wrong Password.");
        }
        //return false;
    }
}

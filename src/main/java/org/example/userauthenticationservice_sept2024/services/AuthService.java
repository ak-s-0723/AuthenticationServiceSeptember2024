package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exception.UserAlreadyExistException;
import org.example.userauthenticationservice_sept2024.exception.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exception.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email  , String password) {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistException("User with email: " + email + " already exists");
        }
        User user = new User();
        user.setEmail(email);
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);

        return true;

    }

    public boolean login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) {
            throw new UserNotFoundException("User with email: " + email + " not found"); }
        boolean matches = bCryptPasswordEncoder.matches(password,userOptional.get().getPassword());//password.equals(userOptional.get().getPassword());
        if(matches){
            return true;
        }else{
            throw new WrongPasswordException("Wrong Password.");
        }
//        User user = userOptional.get();
//        if(password.equals(user.getPassword())) {
//            String token = email + ":" + password;
//            return token;
//        }else{
//            throw new WrongPasswordException("Wrong password");
//        }
        //return false;
    }
}

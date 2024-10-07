package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;

import java.util.Optional;

public class AuthService {
    private UserRepository userRepository;
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email  , String password) {
//        if(userRepository.findByEmail(email).isPresent()) {
//            throw new User
//        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return true;

    }

    public boolean login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        boolean matches = password.equals(userOptional.get().getPassword());
        if(matches){
            return true;
        }else{
            throw new RuntimeException("Wrong Password.");
        }
       // return false;
    }
}

package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.respositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email, String password) throws Exception {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new Exception("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String password) throws Exception {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new Exception("User is not found");
        }
        boolean matches = user.get().getPassword().equals(password);
        if(matches) {
            return true;
        } else{
            throw new Exception("Wrong password");
        }
    }
}

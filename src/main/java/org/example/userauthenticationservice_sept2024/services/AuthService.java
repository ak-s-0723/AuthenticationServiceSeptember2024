package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repos.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public boolean signUp(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }
}

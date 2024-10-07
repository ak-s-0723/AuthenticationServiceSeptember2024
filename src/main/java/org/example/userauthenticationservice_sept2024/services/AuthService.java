package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    public boolean signIn(String username, String password) throws Exception {
        if(userRepository.findByEmail(username).isPresent()){
            throw new Exception();
        }

        User user = new User();
        user.setEmail(username);
        user.setPassword(password);
        userRepository.save(user);
        return true;
    }
}

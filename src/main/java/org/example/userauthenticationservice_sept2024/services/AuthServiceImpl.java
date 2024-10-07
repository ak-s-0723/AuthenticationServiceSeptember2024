package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean signUp(String username, String password) {
        return false;
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }
}

package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.models.User;

public interface IAuthService {
    User signup(User user);
    String login(String email, String password);
}

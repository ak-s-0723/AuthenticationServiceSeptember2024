package org.example.userauthenticationservice_sept2024.service;

public interface IAuthService {
    boolean signUp(String email, String password);
    String login(String email, String password);
}

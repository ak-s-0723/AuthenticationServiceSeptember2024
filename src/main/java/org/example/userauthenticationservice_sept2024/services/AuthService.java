package org.example.userauthenticationservice_sept2024.services;

public interface AuthService {
    boolean signUp(String username, String password);
    boolean login(String username, String password);
}

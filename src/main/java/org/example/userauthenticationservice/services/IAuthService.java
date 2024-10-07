package org.example.userauthenticationservice.services;

public interface IAuthService {
    boolean signUp(String email, String password);
    String login(String email, String password);
}
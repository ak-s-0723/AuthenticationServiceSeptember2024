package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    boolean signUp(String username, String password) throws UserAlreadyExistsException;
    boolean login(String username, String password) throws UserNotFoundException, WrongPasswordException;
}

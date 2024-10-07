package org.example.userauthenticationservice_sept2024.service;

import jakarta.transaction.Transactional;
import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService implements IAuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserAuthenticationService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        userRepository.save(newUser);
        return true;
    }

    @Override
    public String login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> user = userRepository.findByEmail(email);
        User foundUser;

        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        foundUser = user.get();

        if(!passwordEncoder.matches(password, foundUser.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }

        String token = foundUser.getEmail() + ":" + foundUser.getPassword();
        return token;
    }
}
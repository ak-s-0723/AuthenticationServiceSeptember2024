package org.example.userauthenticationservice.services;

import org.example.userauthenticationservice.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice.exceptions.UserNotFoundException;
import org.example.userauthenticationservice.exceptions.WrongPasswordException;
import org.example.userauthenticationservice.models.User;
import org.example.userauthenticationservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAuthenticationService implements IAuthService {
    private final UserRepository userRepository;

    public UserAuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
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

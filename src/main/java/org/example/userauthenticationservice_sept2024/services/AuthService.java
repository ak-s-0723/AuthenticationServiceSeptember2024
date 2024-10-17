package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.respositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bcryptPasswordEncoder;
    }

    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = new User();
        user.setEmail(email);
        //user.setPassword(password);
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User is not found");
        }
        //boolean matches = user.get().getPassword().equals(password);
        Boolean matches = bCryptPasswordEncoder.matches(password, user.get().getPassword());
        if(matches) {
            return true;
        } else{
            throw new WrongPasswordException("Wrong password");
        }
    }
}

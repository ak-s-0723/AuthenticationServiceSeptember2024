package org.example.userauthenticationservice_sept2024.services;

import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        if(userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException("User with email: " + email + " already exists");
        }

        String hashedPassword = bcryptPasswordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }

    public String login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw new UserNotFoundException("User with email: " + email + " not found.");
        }

//        boolean matches = userOptional.get().getPassword().equals(password);
        boolean matches = bcryptPasswordEncoder.matches(password,userOptional.get().getPassword());
        if(matches){
            String token = email + ":" + password;
            return token;
        }else {
            throw new WrongPasswordException ("Wrong Password");
        }
        //return false;
    }
}

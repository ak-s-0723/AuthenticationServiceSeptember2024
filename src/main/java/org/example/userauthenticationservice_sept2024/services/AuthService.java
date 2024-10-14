package org.example.userauthenticationservice_sept2024.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private UserRepository userRepository;
    @Autowired
    private final BCryptPasswordEncoder passwordEncoder;
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public boolean signUp(String email, String password) throws UserAlreadyExistsException,WrongPasswordException {
        if(userRepository.findByEmail(email).isPresent()){
            throw new UserAlreadyExistsException("User with email "+email+" already exists");
        }
        User user = new User();
        user.setEmail(email);
        String hashedPassword = passwordEncoder.encode(password);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }
    public String login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        var user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            throw new UserNotFoundException();
        }
        var matches = passwordEncoder.matches(password, user.get().getPassword());
        if(matches) {
            //var message = email + ":" + password;
            Map<String,Object> claims = new HashMap<>();
            claims.put("iat",System.currentTimeMillis());
            claims.put("exp", System.currentTimeMillis()+846000);
            claims.put("email", user.get().getEmail());
            claims.put("user_id",user.get().getId());
            claims.put("issuer","scaler");
            //byte[] content = message.getBytes();
            MacAlgorithm algorithm = Jwts.SIG.HS256;
            SecretKey secretKey = algorithm.key().build();
            return Jwts.builder().claims(claims).signWith(secretKey).compact();
        }
        else throw new WrongPasswordException("Wrong password");
    }

    //JWT gen
}

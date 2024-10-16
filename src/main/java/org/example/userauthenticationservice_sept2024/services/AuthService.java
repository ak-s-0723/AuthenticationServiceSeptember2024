package org.example.userauthenticationservice_sept2024.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import jakarta.transaction.Transactional;
import org.example.userauthenticationservice_sept2024.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.exceptions.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.exceptions.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.Session;
import org.example.userauthenticationservice_sept2024.models.SessionState;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.SessionRepository;
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
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    SecretKey secretKey;
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
    @Transactional
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
            var token = Jwts.builder().claims(claims).signWith(secretKey).compact();
            Session session = new Session();
            session.setUser(user.get());
            session.setToken(token);
            session.setSessionState(SessionState.ACTIVE);
            sessionRepository.save(session);
            return token;
        }
        else throw new WrongPasswordException("Wrong password");
    }

    //JWT gen
    public boolean validateToken(Long userId,String token){
        var session = sessionRepository.findByTokenAndUser_Id(token,userId);
        if(session.isEmpty()) return false;
        var parsedToken = Jwts.parser().verifyWith(secretKey).build();
        var claims = parsedToken.parseSignedClaims(token).getPayload();
        Long exp = (Long) claims.get("exp");
        if(System.currentTimeMillis()>exp){
            session.get().setSessionState(SessionState.EXPIRED);
            return false;
        }
        return true;
    }
}

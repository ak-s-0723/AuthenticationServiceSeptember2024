package org.example.userauthenticationservice_sept2024.services;



import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import io.jsonwebtoken.security.SecretJwk;
import org.antlr.v4.runtime.misc.Pair;
import org.example.userauthenticationservice_sept2024.Exception.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.Exception.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.Exception.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    public AuthService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        if( userRepository.findByEmail(email).isPresent() ) {
            throw new UserAlreadyExistsException("User with email : "+email+" already present in the system");
        }
        String hashedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }

    public Pair<Boolean,String> login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if( userOptional.isEmpty() ) {
            throw new UserNotFoundException("User with email "+email+"not found in the system");
        }
        User user = userOptional.get();

        boolean matches = bCryptPasswordEncoder.matches(password, user.getPassword());

        //JWT Generation
//        String message = "{\n" +
//                "   \"email\": \"anurag@gmail.com\",\n" +
//                "   \"roles\": [\n" +
//                "      \"instructor\",\n" +
//                "      \"ta\"\n" +
//                "   ],\n" +
//                "   \"expirationDate\": \"2ndApril2025\"\n" +
//                "}";
//
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        MacAlgorithm macAlgorithm = Jwts.SIG.HS256;
        SecretKey secretKey = macAlgorithm.key().build();

        Map<String, Object> claims = new HashMap<>();
        Long currentTimeMillis = System.currentTimeMillis();
        claims.put("iat", currentTimeMillis);
        claims.put("exp", currentTimeMillis + 864000);
        claims.put("User_id",user.getId());
        claims.put("Issuer","Scaler");
        String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

        if( matches ) {
            return new Pair<Boolean,String>(true,token);
        }
//        return false;
        else{
            throw new WrongPasswordException("Wrong password");
        }

//        if( password.equals(user.getPassword()) ) {
//            String token = email+":"+password;
//            return token;
//        }
//        else{
//            throw new WrongPasswordException("Wrong password");
//        }
//        boolean matches = password.equals(userOptional.get().getPassword());
//        if( matches ) {
//            return true;
//        }
//        return false;
    }

}

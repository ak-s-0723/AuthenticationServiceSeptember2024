package org.example.userauthenticationservice.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.example.userauthenticationservice.exceptions.UserAlreadyExistsException;
import org.example.userauthenticationservice.exceptions.UserNotFoundException;
import org.example.userauthenticationservice.exceptions.WrongPasswordException;
import org.example.userauthenticationservice.models.User;
import org.example.userauthenticationservice.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserAuthenticationService implements IAuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserAuthenticationService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(bCryptPasswordEncoder.encode(password));
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

        if(!bCryptPasswordEncoder.matches(password, foundUser.getPassword())) {
            throw new WrongPasswordException("Wrong password");
        }

        //JWT Token Generation

        MacAlgorithm algorithm = Jwts.SIG.HS256;
        SecretKey secretKey = algorithm.key().build();

        Map<String, Object> claims = new HashMap<>();
        Long currentTime = System.currentTimeMillis();
        claims.put("iat", currentTime);
        claims.put("exp", currentTime + 2592000);
        claims.put("user_id", foundUser.getId());
        claims.put("issuer", "scaler");

        String jwtToken = Jwts.builder().claims(claims).signWith(secretKey).compact();


//        String token = foundUser.getEmail() + ":" + foundUser.getPassword();
        return jwtToken;
    }
}

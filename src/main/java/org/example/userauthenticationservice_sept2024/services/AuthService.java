package org.example.userauthenticationservice_sept2024.services;



import org.example.userauthenticationservice_sept2024.Exception.UserAlreadyExistsException;
import org.example.userauthenticationservice_sept2024.Exception.UserNotFoundException;
import org.example.userauthenticationservice_sept2024.Exception.WrongPasswordException;
import org.example.userauthenticationservice_sept2024.models.User;
import org.example.userauthenticationservice_sept2024.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

//    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean signUp(String email, String password) throws UserAlreadyExistsException {
        if( userRepository.findByEmail(email).isPresent() ) {
            throw new UserAlreadyExistsException("User with email : "+email+" already present in the system");
        }
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);
        return true;
    }

    public boolean login(String email, String password) throws UserNotFoundException, WrongPasswordException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if( userOptional.isEmpty() ) {
            throw new UserNotFoundException("User with email "+email+"not found in the system");
        }
        User user = userOptional.get();

        if( passwordEncoder.matches(password, user.getPassword()) ) {
            return true;
        }
        return false;
//        else{
//            throw new WrongPasswordException("Wrong password");
//        }

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

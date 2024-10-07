package org.example.userauthenticationservice_sept2024.repos;

import org.example.userauthenticationservice_sept2024.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    User save(User user);


}

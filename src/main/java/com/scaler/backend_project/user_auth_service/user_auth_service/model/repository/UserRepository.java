package com.scaler.backend_project.user_auth_service.user_auth_service.model.repository;

import com.scaler.backend_project.user_auth_service.user_auth_service.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

package org.example.userauthenticationservice_sept2024.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class User extends BaseModel {
    private String email;
    private String password;
//    private UserRole role;
}

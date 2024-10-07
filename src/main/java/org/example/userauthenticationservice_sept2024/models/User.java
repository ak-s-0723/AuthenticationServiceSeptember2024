package org.example.userauthenticationservice_sept2024.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel{
    private String email;
    private String password;
}

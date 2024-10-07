package org.example.userauthenticationservice_sept2024.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonDeserialize(as = User.class)
public class User extends BaseModel{
    private String email;
    private String password;
}

package org.example.userauthenticationservice_sept2024.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@JsonDeserialize(as = User.class)
@Setter
@Getter
public class User extends BaseModel{
    private String email;
    private String password;
}

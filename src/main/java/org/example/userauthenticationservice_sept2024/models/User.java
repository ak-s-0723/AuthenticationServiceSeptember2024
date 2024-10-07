package org.example.userauthenticationservice_sept2024.models;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@JsonDeserialize(as = User.class)
@Setter
@Getter
public class User extends BaseModel {

    public String email;
    public String password;

}

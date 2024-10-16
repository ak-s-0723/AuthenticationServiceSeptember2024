package org.example.userauthenticationservice_sept2024.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Session extends BaseModel{
    private SessionState sessionState;
    private String token;
    @ManyToOne()
    User user;
}

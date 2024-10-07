package com.scaler.backend_project.user_auth_service.user_auth_service.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@JsonDeserialize(as = User.class)
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Role> roles = new HashSet<>();
}

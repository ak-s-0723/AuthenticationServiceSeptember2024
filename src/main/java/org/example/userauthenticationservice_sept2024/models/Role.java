package org.example.userauthenticationservice_sept2024.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@JsonDeserialize(as = Role.class)
@Data
public class Role extends BaseModel{
    private String name; // Getters and Setters
 }

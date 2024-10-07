package org.example.userauthenticationservice_sept2024.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@JsonDeserialize(as = Role.class)
@Data
public class Role extends BaseModel{
    private String name;
}

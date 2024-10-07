package org.example.userauthenticationservice_sept2024.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "roles")
@JsonDeserialize(as = Role.class)
public class Role extends BaseModel {
    @Enumerated(EnumType.ORDINAL)
    private RoleName roleName;
}
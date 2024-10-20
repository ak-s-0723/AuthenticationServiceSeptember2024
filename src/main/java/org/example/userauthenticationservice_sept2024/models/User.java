package org.example.userauthenticationservice_sept2024.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(as = User.class)
public class User extends BaseModel {
    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Message should not be empty")
    private String password;
    @NotNull(message = "Email should not be null")
    @NotEmpty(message = "Email should not be empty")
    private String email;
}

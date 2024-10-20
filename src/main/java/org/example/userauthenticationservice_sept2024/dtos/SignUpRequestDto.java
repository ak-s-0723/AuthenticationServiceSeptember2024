package org.example.userauthenticationservice_sept2024.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @NotNull(message = "Email should not be null")
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @NotNull(message = "Password should not be null")
    @NotEmpty(message = "Password should not be empty")
    private String password;
}

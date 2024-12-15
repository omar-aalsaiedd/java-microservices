package com.auth.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDTO {

    @NotNull(message = "username is required")
    @Size(min = 5, max = 12)
    private String username;

    @NotNull(message = "email is required")
    @Email
    private String email;


    @NotNull(message = "password is required")
    @Size(min = 8, message = "password can't be less than 8 characters")
    private String password;
}

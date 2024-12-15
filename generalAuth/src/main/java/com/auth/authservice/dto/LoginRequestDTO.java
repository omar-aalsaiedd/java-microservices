package com.auth.authservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {

    @NotNull(message = "mmm should i guess your username?")
    @Size(min = 5, max = 12, message = "length not matching the criteria")
    private String username;

    @NotNull(message = "password is required")
    @Size(min = 8, message = "password can't be less than 8 characters")
    private String password;
}

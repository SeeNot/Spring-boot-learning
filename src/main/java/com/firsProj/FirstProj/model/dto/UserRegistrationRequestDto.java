package com.firsProj.FirstProj.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegistrationRequestDto(
        @NotBlank String username,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 8, message = "Password is too short") String password
) {
}

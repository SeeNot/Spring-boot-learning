package com.firsProj.FirstProj.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookRequestDto(
        @NotBlank(message = "Title can't be empty") String title,
        @NotNull(message = "Author can't be null") String author,
        Long userId
) {
}

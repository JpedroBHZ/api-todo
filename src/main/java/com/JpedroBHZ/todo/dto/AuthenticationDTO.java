package com.JpedroBHZ.todo.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "O login não pode ser vazio")
        String login,

        @NotBlank(message = "A senha não pode ser vazia")
        String password
) {}
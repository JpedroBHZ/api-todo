package com.JpedroBHZ.todo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TaskRequestDTO(
        @NotBlank(message = "A descrição da tarefa é obrigatória.")
        @Size(min = 5, max = 100, message = "A descrição deve ter entre 5 e 100 caracteres.")
        String description
) {}
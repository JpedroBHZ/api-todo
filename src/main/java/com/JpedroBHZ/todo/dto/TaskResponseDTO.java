package com.JpedroBHZ.todo.dto;

public record TaskResponseDTO(
        Long id,
        String description,
        boolean completed
) {}

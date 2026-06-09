package com.JpedroBHZ.todo.infra.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    // Trata erros de validação (Bean Validation - @Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationEntity(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400

        // Pega todas as mensagens de erro dos campos inválidos e junta em uma única String limpa
        String errorMessage = e.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(" "));

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                "Validation Error",
                errorMessage,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}
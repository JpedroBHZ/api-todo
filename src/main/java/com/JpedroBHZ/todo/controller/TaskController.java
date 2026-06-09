package com.JpedroBHZ.todo.controller;

import com.JpedroBHZ.todo.dto.TaskRequestDTO;
import com.JpedroBHZ.todo.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.JpedroBHZ.todo.service.TaskService;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<TaskResponseDTO> getAll() {
        return service.listAll();
    }

    @PostMapping
    public TaskResponseDTO create(@Valid @RequestBody TaskRequestDTO request) {
        return service.save(request);
    }
}
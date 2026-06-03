package com.JpedroBHZ.todo.controller;

import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.service.TaskService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    // Rota GET para listar tudo (http://localhost:8080/tasks)
    @GetMapping
    public List<Task> getAll() {
        return service.listAll();
    }

    // Rota POST para criar uma tarefa (http://localhost:8080/tasks)
    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.save(task);
    }
}
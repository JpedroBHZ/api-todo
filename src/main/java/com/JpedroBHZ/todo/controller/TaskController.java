package com.JpedroBHZ.todo.controller;

import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService service;

    @GetMapping
    public List<Task> getAll() {
        return service.listAll();
    }

    @PostMapping
    public Task create(@RequestBody Task task) {
        return service.save(task);
    }

}
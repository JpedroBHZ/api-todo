package com.JpedroBHZ.todo.service;

import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Cria o construtor para o 'repository' por debaixo dos panos
public class TaskService {

    private final TaskRepository repository;

    public List<Task> listAll() {
        return repository.findAll();
    }

    public Task save(Task task) {
        return repository.save(task);
    }
}
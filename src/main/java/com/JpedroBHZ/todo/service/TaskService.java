package com.JpedroBHZ.todo.service;

import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    // Injeção de dependência via construtor (Boa prática!)
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    // Método para listar tudo
    public List<Task> listAll() {
        return repository.findAll();
    }

    // Método para salvar uma nova tarefa
    public Task save(Task task) {
        return repository.save(task);
    }
}

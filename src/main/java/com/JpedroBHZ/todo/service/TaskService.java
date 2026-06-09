package com.JpedroBHZ.todo.service;

import com.JpedroBHZ.todo.dto.TaskRequestDTO;
import com.JpedroBHZ.todo.dto.TaskResponseDTO;
import com.JpedroBHZ.todo.infra.exceptions.ResourceNotFoundException;
import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public Page<TaskResponseDTO> listAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(task -> new TaskResponseDTO(task.getId(), task.getDescription(), task.isCompleted()));
    }

    public TaskResponseDTO save(TaskRequestDTO request) {
        // Converte o DTO de entrada para a Entidade que o banco entende
        Task task = new Task();
        task.setDescription(request.description());
        task.setCompleted(false); // Toda tarefa nova nasce sem estar concluída

        Task savedTask = repository.save(task);

        // Retorna o DTO de resposta formatado
        return new TaskResponseDTO(savedTask.getId(), savedTask.getDescription(), savedTask.isCompleted());
    }

    public TaskResponseDTO findById(Long id) {
        Task task = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa com o ID " + id + " não foi encontrada."));

        return new TaskResponseDTO(task.getId(), task.getDescription(), task.isCompleted());
    }
}
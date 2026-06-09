package com.JpedroBHZ.todo.service;

import com.JpedroBHZ.todo.dto.TaskRequestDTO;
import com.JpedroBHZ.todo.dto.TaskResponseDTO;
import com.JpedroBHZ.todo.infra.exceptions.ResourceNotFoundException;
import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.model.User;
import com.JpedroBHZ.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    // Método auxiliar privado para pegar o usuário logado da sessão do Spring Security
    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Page<TaskResponseDTO> listAll(Pageable pageable) {
        User userLogado = getAuthenticatedUser();

        // CORREÇÃO: Busca apenas as tarefas que pertencem ao usuário logado
        return repository.findByUser(userLogado, pageable)
                .map(task -> new TaskResponseDTO(task.getId(), task.getDescription(), task.isCompleted()));
    }

    public TaskResponseDTO save(TaskRequestDTO request) {
        User userLogado = getAuthenticatedUser();

        Task task = new Task();
        task.setDescription(request.description());
        task.setCompleted(false);
        task.setUser(userLogado); // CORREÇÃO: Vincula a nova tarefa ao dono dela

        Task savedTask = repository.save(task);

        return new TaskResponseDTO(savedTask.getId(), savedTask.getDescription(), savedTask.isCompleted());
    }

    public TaskResponseDTO findById(Long id) {
        User userLogado = getAuthenticatedUser();

        // CORREÇÃO: Evita que um usuário malicioso descubra tarefas de outros digitando o ID na URL
        Task task = repository.findByIdAndUser(id, userLogado)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa com o ID " + id + " não foi encontrada para o seu usuário."));

        return new TaskResponseDTO(task.getId(), task.getDescription(), task.isCompleted());
    }
}
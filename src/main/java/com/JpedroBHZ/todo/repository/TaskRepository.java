package com.JpedroBHZ.todo.repository;

import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository; // ou JpaRepository
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Busca as tarefas paginadas filtrando pelo usuário
    Page<Task> findByUser(User user, Pageable pageable);

    // Garante a busca por ID apenas se pertencer ao usuário correto
    Optional<Task> findByIdAndUser(Long id, User user);
}
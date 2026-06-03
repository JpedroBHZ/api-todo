package com.JpedroBHZ.todo.repository;

import com.JpedroBHZ.todo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Só de estender o JpaRepository, você já ganha os métodos prontos!
}
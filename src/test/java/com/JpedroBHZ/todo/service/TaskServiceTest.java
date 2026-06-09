package com.JpedroBHZ.todo.service;

import com.JpedroBHZ.todo.dto.TaskRequestDTO;
import com.JpedroBHZ.todo.dto.TaskResponseDTO;
import com.JpedroBHZ.todo.infra.exceptions.ResourceNotFoundException;
import com.JpedroBHZ.todo.model.Task;
import com.JpedroBHZ.todo.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    @Test
    @DisplayName("Deve salvar uma tarefa com sucesso quando os dados forem válidos")
    void saveWithSuccess() {
        // Arrange (Preparação dos dados)
        TaskRequestDTO request = new TaskRequestDTO("Estudar JUnit e Mockito no Java");

        Task savedTask = new Task();
        savedTask.setId(1L);
        savedTask.setDescription(request.description());
        savedTask.setCompleted(false);

        // Simulando o comportamento do Repository (Mock)
        when(repository.save(any(Task.class))).thenReturn(savedTask);

        // Act (Execução da ação que queremos testar)
        TaskResponseDTO response = service.save(request);

        // Assert (Verificações se tudo ocorreu como esperado)
        assertNotNull(response);
        assertEquals(1L, response.id());
        assertEquals("Estudar JUnit e Mockito no Java", response.description());
        assertFalse(response.completed());

        // Garante que o repository foi chamado exatamente 1 vez
        verify(repository, times(1)).save(any(Task.class));
    }

    @Test
    @DisplayName("Deve lançar ResourceNotFoundException quando o ID da tarefa não existir")
    void findByIdThrowsExceptionWhenIdNotFound() {
        // Arrange
        Long invalidId = 99L;

        // Simulando o banco retornando um Optional vazio (Null)
        when(repository.findById(invalidId)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        // Verificamos se a chamada do método lança a exceção esperada
        assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(invalidId);
        });

        // Garante que o repositório foi consultado corretamente
        verify(repository, times(1)).findById(invalidId);
    }
}
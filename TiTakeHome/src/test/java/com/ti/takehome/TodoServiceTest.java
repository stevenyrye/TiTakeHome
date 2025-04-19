package com.ti.takehome;
import com.ti.takehome.persistence.entity.Todo;
import com.ti.takehome.persistence.repository.TodoRepository;
import com.ti.takehome.service.TodoService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { TodoService.class, TodoRepository.class })
public class TodoServiceTest {

    @Autowired
    private TodoService todoService;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    void getAllTodos_shouldReturnList() {
        List<Todo> mockTodos = Arrays.asList(
            new Todo(1l, "Task 1", false),
            new Todo(2l, "Task 2", true)
        );

        when(todoRepository.findAll()).thenReturn(mockTodos);

        List<Todo> result = todoService.getAllTodos();

        assertEquals(2, result.size());
        assertEquals("Task 1", result.get(0).getDescription());
    }

    @Test
    void getTodoById_shouldReturnTodo_whenExists() {
        Todo todo = new Todo(1l, "Test Task", false);
        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Optional<Todo> result = todoService.getTodoById(1L);

        assertTrue(result.isPresent());
        assertEquals("Test Task", result.get().getDescription());
    }

    @Test
    void getTodoById_shouldReturnEmpty_whenNotFound() {
        when(todoRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Todo> result = todoService.getTodoById(999L);

        assertFalse(result.isPresent());
    }

    @Test
    void saveTodo_shouldReturnSavedTodo() {
        Todo input = new Todo("Create test", false);
        Todo saved = new Todo(1l, "Create test", false);

        when(todoRepository.save(input)).thenReturn(saved);

        Todo result = todoService.createTodo(input);

        assertNotNull(result.getId());
        assertEquals("Create test", result.getDescription());
    }

//    @Test
//    void deleteTodo_shouldCallRepository() {
//        Long id = 123L;
//        todoService.deleteTodo(id);
//        verify(todoRepository, times(1)).deleteById(id);
//    }
}
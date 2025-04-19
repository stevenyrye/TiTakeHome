package com.ti.takehome.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ti.takehome.persistence.entity.Todo;
import com.ti.takehome.persistence.repository.TodoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    TodoRepository todoRepository;

 
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> updateTodo(Long id, Todo updatedTodo) {
        int updatedRows = todoRepository.updateTodo(id, updatedTodo.getDescription(), updatedTodo.isCompleted());
        if (updatedRows > 0) {
            return todoRepository.findById(id); // Fetch updated entity
        }
        return Optional.empty();
    }

    public boolean deleteTodo(Long id) {
        return todoRepository.deleteTodo(id) > 0;
    }
 }
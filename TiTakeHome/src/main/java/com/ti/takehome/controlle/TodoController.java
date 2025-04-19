package com.ti.takehome.controlle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ti.takehome.persistence.entity.Todo;
import com.ti.takehome.service.TodoService;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping(value = "/echo", produces = { "application/json" })
	  public ResponseEntity<Object> Employee() {
		  return new ResponseEntity<>(HttpStatus.OK);
	  }

    @GetMapping(produces = { "application/json" })
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @GetMapping(value = "/{id}", produces = { "application/json" })
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
        return todoService.getTodoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping(value = "/{id}", produces = { "application/json" })
    public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo updatedTodo) {
        return todoService.updateTodo(id, updatedTodo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(value = "/{id}", produces = { "application/json" })
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        return todoService.deleteTodo(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
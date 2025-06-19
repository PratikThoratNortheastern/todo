package com.example.todo.controller;

import com.example.todo.model.Todo;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "*") // Allow frontend to access this from any origin
public class TodoController {

  @Autowired
  private TodoService todoService;

  // GET /api/todos
  @GetMapping
  public List<Todo> getAllTodos() {
    return todoService.getAllTodos();
  }

  // GET /api/todos/{id}
  @GetMapping("/{id}")
  public ResponseEntity<Todo> getTodoById(@PathVariable Long id) {
    return todoService.getTodoById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // POST /api/todos
  @PostMapping
  public Todo createTodo(@RequestBody Todo todo) {
    return todoService.createTodo(todo);
  }

  // PUT /api/todos/{id}
  @PutMapping("/{id}")
  public ResponseEntity<Todo> updateTodo(@PathVariable Long id, @RequestBody Todo todo) {
    return todoService.updateTodo(id, todo)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
  }

  // DELETE /api/todos/{id}
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
    todoService.deleteTodo(id);
    return ResponseEntity.noContent().build();
  }
}

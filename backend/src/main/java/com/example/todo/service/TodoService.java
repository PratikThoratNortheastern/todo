package com.example.todo.service;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

  @Autowired
  private TodoRepository todoRepository;

  // Get all todos
  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  // Get todo by ID
  public Optional<Todo> getTodoById(Long id) {
    return todoRepository.findById(id);
  }

  // Create new todo
  public Todo createTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  // Update existing todo
  public Optional<Todo> updateTodo(Long id, Todo updatedTodo) {
    return todoRepository.findById(id).map(existingTodo -> {
      existingTodo.setTitle(updatedTodo.getTitle());
      existingTodo.setCompleted(updatedTodo.isCompleted());
      return todoRepository.save(existingTodo);
    });
  }

  // Delete a todo
  public void deleteTodo(Long id) {
    todoRepository.deleteById(id);
  }
}

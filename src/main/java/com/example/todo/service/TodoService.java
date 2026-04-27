package com.example.todo.service;

import com.example.todo.mapper.TodoMapper;
import com.example.todo.model.Todo;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoMapper todoMapper;

    public TodoService(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    public void insert(Todo todo) {
        todoMapper.insert(todo);
    }
}

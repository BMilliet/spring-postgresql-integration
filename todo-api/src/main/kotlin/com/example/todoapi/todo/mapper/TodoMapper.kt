package com.example.todoapi.todo.mapper

import com.example.todoapi.todo.dto.request.TodoRequestDTO
import com.example.todoapi.todo.dto.response.TodoResponseDTO
import com.example.todoapi.todo.model.Todo

fun TodoRequestDTO.toModel() =
    Todo(
        title = title,
        description = description,
        dueDate = dueDate
    )

fun Todo.toResponse() =
    TodoResponseDTO(
        id = id,
        title = title,
        description = description,
        createdAt = createdAt,
        dueDate = dueDate
    )
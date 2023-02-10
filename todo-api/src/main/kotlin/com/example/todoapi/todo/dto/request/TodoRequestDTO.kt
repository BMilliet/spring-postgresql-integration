package com.example.todoapi.todo.dto.request

import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class TodoRequestDTO(
    @field: NotEmpty(message = "Title cant be empty")
    @field: Size(min = 5, max = 100, message = "Title must have between 5 and 100 characters")
    val title: String,
    val description: String = "",
    val dueDate: LocalDateTime?
)
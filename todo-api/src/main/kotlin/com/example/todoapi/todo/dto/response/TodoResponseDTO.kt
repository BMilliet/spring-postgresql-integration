package com.example.todoapi.todo.dto.response

import java.time.LocalDateTime
import java.util.UUID

data class TodoResponseDTO(
    val id: UUID,
    var title: String,
    var description: String,
    val createdAt: LocalDateTime,
    var dueDate: LocalDateTime?
)
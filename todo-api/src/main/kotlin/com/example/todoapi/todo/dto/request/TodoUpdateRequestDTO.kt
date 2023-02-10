package com.example.todoapi.todo.dto.request

import java.time.LocalDateTime
import java.util.UUID

data class TodoUpdateRequestDTO(
    val id: UUID,
    val title: String?,
    val description: String?,
    val dueDate: LocalDateTime?
)
package com.example.todoapi.todo.model

import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Todo(
    @Id
    val id: UUID = UUID.randomUUID(),
    var title: String,
    var description: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var dueDate: LocalDateTime?
)
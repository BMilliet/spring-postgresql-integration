package com.example.todoapi.todo.repository

import com.example.todoapi.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface TodoRepository: JpaRepository<Todo, UUID>
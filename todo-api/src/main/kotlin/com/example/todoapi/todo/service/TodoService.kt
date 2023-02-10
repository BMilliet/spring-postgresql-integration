package com.example.todoapi.todo.service

import com.example.todoapi.todo.dto.request.TodoRequestDTO
import com.example.todoapi.todo.dto.request.TodoUpdateRequestDTO
import com.example.todoapi.todo.mapper.toModel
import com.example.todoapi.todo.model.Todo
import com.example.todoapi.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TodoService(private val todoRepository: TodoRepository) {

    fun list(): List<Todo> = todoRepository.findAll()

    fun findById(id: UUID): Todo = todoRepository.findById(id).orElseThrow()

    fun add(todoRequestDTO: TodoRequestDTO): Todo =
        todoRepository.save(todoRequestDTO.toModel())

    fun delete(id: UUID) = todoRepository.deleteById(id)

    fun update(todoUpdateRequestDTO: TodoUpdateRequestDTO): Todo {
        val itemToUpdate = todoRepository.findById(todoUpdateRequestDTO.id).orElseThrow()
        todoUpdateRequestDTO.title?.let { itemToUpdate.title = it }
        todoUpdateRequestDTO.dueDate?.let { itemToUpdate.dueDate = it }
        todoUpdateRequestDTO.description?.let { itemToUpdate.description = it }
        return itemToUpdate
    }
}
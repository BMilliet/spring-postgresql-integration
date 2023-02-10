package com.example.todoapi.todo.controller

import com.example.todoapi.todo.dto.request.TodoRequestDTO
import com.example.todoapi.todo.dto.request.TodoUpdateRequestDTO
import com.example.todoapi.todo.mapper.toResponse
import com.example.todoapi.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RestController
@RequestMapping("/todos")
class TodoController(private val todoService: TodoService) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun list() = todoService.list().map { it.toResponse() }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable id: UUID) = todoService.findById(id).toResponse()

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: UUID) = todoService.delete(id)

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    fun add(
        @RequestBody
        @Valid
        todoRequestDTO: TodoRequestDTO
    ) = todoService.add(todoRequestDTO)

    @PutMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    fun update(
        @RequestBody
        @Valid
        todoUpdateRequestDTO: TodoUpdateRequestDTO
    ) = todoService.update(todoUpdateRequestDTO).toResponse()
}
package com.example.todoapi

import com.example.todoapi.todo.dto.request.TodoRequestDTO
import com.example.todoapi.todo.dto.request.TodoUpdateRequestDTO
import com.example.todoapi.todo.model.Todo
import com.example.todoapi.todo.repository.TodoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var todoRepository: TodoRepository

    @Test
    fun `test list`() {
        todoRepository.deleteAll()

        todoRepository.save(
            Todo(
                UUID.randomUUID(),
                "study kotlin",
                "how to integrate postgresql to spring boot",
                LocalDateTime.now(),
                null
            )
        )

        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$").isArray)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].id").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].title").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].description").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].createdAt").isString)
            .andExpect(MockMvcResultMatchers.jsonPath("\$[0].dueDate").isEmpty)
    }

    @Test
    fun `test add`() {
        todoRepository.deleteAll()

        val todoRequest = TodoRequestDTO(
            "study kotlin",
            "how to integrate postgresql to spring boot",
            null
        )

        val todoJSON = ObjectMapper()
            .writeValueAsString(todoRequest)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/todos")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(todoJSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.title").value(todoRequest.title))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.description").value(todoRequest.description))
    }

    @Test
    fun `test find`() {
        todoRepository.deleteAll()

        val todo = todoRepository.save(
            Todo(
                UUID.randomUUID(),
                "study kotlin",
                "how to integrate postgresql to spring boot",
                LocalDateTime.now(),
                null
            )
        )

        mockMvc.perform(MockMvcRequestBuilders.get("/todos/${todo.id}"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.title").value(todo.title))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.description").value(todo.description))
    }

    @Test
    fun `test delete`() {
        todoRepository.deleteAll()

        val id = UUID.randomUUID()

        todoRepository.save(
            Todo(
                id,
                "study kotlin",
                "how to integrate postgresql to spring boot",
                LocalDateTime.now(),
                null
            )
        )

        mockMvc.perform(MockMvcRequestBuilders.delete("/todos/${id}"))
            .andExpect(MockMvcResultMatchers.status().isNoContent)

        Assertions.assertTrue(todoRepository.findAll().isEmpty())
    }

    @Test
    fun `test update`() {
        todoRepository.deleteAll()

        val id = UUID.randomUUID()

        todoRepository.save(
            Todo(
                id,
                "study kotlin",
                "how to integrate postgresql to spring boot",
                LocalDateTime.now(),
                null
            )
        )

        val newTodo = TodoUpdateRequestDTO(
            id,
            "study kotlin spring",
            null,
            null
        )

        val newTodoJSON = ObjectMapper()
            .writeValueAsString(newTodo)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/todos")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newTodoJSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andExpect(MockMvcResultMatchers.jsonPath("\$.title").value(newTodo.title))
    }
}
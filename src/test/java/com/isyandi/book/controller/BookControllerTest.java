package com.isyandi.book.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isyandi.book.dto.BookCreateRequest;
import com.isyandi.book.dto.BookResponse;
import com.isyandi.book.exception.BookNotFoundException;
import com.isyandi.book.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    private BookResponse sampleResponse;
    private BookCreateRequest createRequest;

    @BeforeEach
    void setUp() {
        sampleResponse = BookResponse.builder()
                .id(1L)
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("978-0132350884")
                .publishedDate(LocalDate.of(2008, 8, 1))
                .build();

        createRequest = BookCreateRequest.builder()
                .title("Clean Code")
                .author("Robert C. Martin")
                .isbn("978-0132350884")
                .publishedDate(LocalDate.of(2008, 8, 1))
                .build();
    }

    @Test
    @DisplayName("POST /api/books - Success (201 Created)")
    void createBook_Success() throws Exception {
        when(bookService.createBook(any())).thenReturn(sampleResponse);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.isbn").value("978-0132350884"));
    }

    @Test
    @DisplayName("GET /api/books - Success (200 OK)")
    void getAllBooks_Success() throws Exception {
        when(bookService.getAllBooks()).thenReturn(List.of(sampleResponse));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].title").value("Clean Code"));
    }

    @Test
    @DisplayName("GET /api/books/{id} - Success (200 OK)")
    void getBookById_Success() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(sampleResponse);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    @DisplayName("GET /api/books/{id} - Not Found (404 Not Found)")
    void getBookById_NotFound() throws Exception {
        when(bookService.getBookById(99L)).thenThrow(new BookNotFoundException(99L));

        mockMvc.perform(get("/api/books/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("NOT_FOUND"));
    }

    @Test
    @DisplayName("DELETE /api/books/{id} - Success (204 No Content)")
    void deleteBook_Success() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNoContent());
    }
}

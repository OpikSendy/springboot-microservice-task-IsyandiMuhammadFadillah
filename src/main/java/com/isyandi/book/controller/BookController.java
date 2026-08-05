package com.isyandi.book.controller;

import com.isyandi.book.dto.BookCreateRequest;
import com.isyandi.book.dto.BookPatchRequest;
import com.isyandi.book.dto.BookResponse;
import com.isyandi.book.dto.BookUpdateRequest;
import com.isyandi.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book Management API", description = "Endpoints for Managing Books Microservice")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Add a new book", description = "Creates a new book record with Title, Author, ISBN, and Published Date.")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookCreateRequest request) {
        BookResponse response = bookService.createBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all registered books.")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", description = "Retrieves details of a specific book by its unique ID.")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        BookResponse response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book by ID (Full Update)", description = "Updates all fields of an existing book by its ID.")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateRequest request) {
        BookResponse response = bookService.updateBook(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partial update of a book by ID", description = "Updates selected fields (e.g. title only) of a book.")
    public ResponseEntity<BookResponse> patchBook(
            @PathVariable Long id,
            @RequestBody BookPatchRequest request) {
        BookResponse response = bookService.patchBook(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by ID", description = "Removes a book record permanently from the database.")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

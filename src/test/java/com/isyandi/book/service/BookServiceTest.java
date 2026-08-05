package com.isyandi.book.service;

import com.isyandi.book.dto.BookCreateRequest;
import com.isyandi.book.dto.BookResponse;
import com.isyandi.book.entity.Book;
import com.isyandi.book.exception.BookNotFoundException;
import com.isyandi.book.exception.DuplicateIsbnException;
import com.isyandi.book.repository.BookRepository;
import com.isyandi.book.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book sampleBook;
    private BookCreateRequest createRequest;

    @BeforeEach
    void setUp() {
        sampleBook = Book.builder()
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
    @DisplayName("Create Book - Success")
    void createBook_Success() {
        when(bookRepository.existsByIsbn(anyString())).thenReturn(false);
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);

        BookResponse response = bookService.createBook(createRequest);

        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("Clean Code", response.getTitle());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    @DisplayName("Create Book - Duplicate ISBN throws DuplicateIsbnException")
    void createBook_DuplicateIsbn_ThrowsException() {
        when(bookRepository.existsByIsbn(anyString())).thenReturn(true);

        assertThrows(DuplicateIsbnException.class, () -> bookService.createBook(createRequest));
        verify(bookRepository, never()).save(any(Book.class));
    }

    @Test
    @DisplayName("Get Book By ID - Success")
    void getBookById_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(sampleBook));

        BookResponse response = bookService.getBookById(1L);

        assertNotNull(response);
        assertEquals(1L, response.getId());
    }

    @Test
    @DisplayName("Get Book By ID - Not Found throws BookNotFoundException")
    void getBookById_NotFound_ThrowsException() {
        when(bookRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(99L));
    }

    @Test
    @DisplayName("Get All Books - Success")
    void getAllBooks_Success() {
        when(bookRepository.findAll()).thenReturn(List.of(sampleBook));

        List<BookResponse> books = bookService.getAllBooks();

        assertEquals(1, books.size());
        assertEquals("Clean Code", books.get(0).getTitle());
    }
}

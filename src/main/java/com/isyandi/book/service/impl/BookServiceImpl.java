package com.isyandi.book.service.impl;

import com.isyandi.book.dto.BookCreateRequest;
import com.isyandi.book.dto.BookPatchRequest;
import com.isyandi.book.dto.BookResponse;
import com.isyandi.book.dto.BookUpdateRequest;
import com.isyandi.book.entity.Book;
import com.isyandi.book.exception.BookNotFoundException;
import com.isyandi.book.exception.DuplicateIsbnException;
import com.isyandi.book.repository.BookRepository;
import com.isyandi.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public BookResponse createBook(BookCreateRequest request) {
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            throw new DuplicateIsbnException(request.getIsbn());
        }

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .isbn(request.getIsbn())
                .publishedDate(request.getPublishedDate())
                .build();

        Book saved = bookRepository.save(book);
        return BookResponse.fromEntity(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        return BookResponse.fromEntity(book);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (bookRepository.existsByIsbnAndIdNot(request.getIsbn(), id)) {
            throw new DuplicateIsbnException(request.getIsbn());
        }

        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPublishedDate(request.getPublishedDate());

        Book updated = bookRepository.save(book);
        return BookResponse.fromEntity(updated);
    }

    @Override
    @Transactional
    public BookResponse patchBook(Long id, BookPatchRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            book.setTitle(request.getTitle());
        }

        if (request.getAuthor() != null && !request.getAuthor().isBlank()) {
            book.setAuthor(request.getAuthor());
        }

        if (request.getIsbn() != null && !request.getIsbn().isBlank()) {
            if (bookRepository.existsByIsbnAndIdNot(request.getIsbn(), id)) {
                throw new DuplicateIsbnException(request.getIsbn());
            }
            book.setIsbn(request.getIsbn());
        }

        if (request.getPublishedDate() != null) {
            book.setPublishedDate(request.getPublishedDate());
        }

        Book patched = bookRepository.save(book);
        return BookResponse.fromEntity(patched);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
        bookRepository.delete(book);
    }
}

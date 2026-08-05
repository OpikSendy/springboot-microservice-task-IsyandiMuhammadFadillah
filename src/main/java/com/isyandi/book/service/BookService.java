package com.isyandi.book.service;

import com.isyandi.book.dto.BookCreateRequest;
import com.isyandi.book.dto.BookPatchRequest;
import com.isyandi.book.dto.BookResponse;
import com.isyandi.book.dto.BookUpdateRequest;

import java.util.List;

public interface BookService {

    BookResponse createBook(BookCreateRequest request);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id, BookUpdateRequest request);

    BookResponse patchBook(Long id, BookPatchRequest request);

    void deleteBook(Long id);
}

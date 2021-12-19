package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.infrastructure.model.BookEntity;
import com.github.fge.jsonpatch.JsonPatch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface BookService {

    Book add(Book book);

    BookEntity getByISBN(String isbn);

    ResponseEntity<BookEntity> updateBook(String isbn, JsonPatch patch);

    Page<BookEntity> getAllBooks(Pageable pageable);

    Page<BookEntity> searchByYear(int year, Pageable pageable);

    Page<BookEntity> searchByAuthorName(String authorName, Pageable pageable);

    Page<BookEntity> searchByCategory(String category, Pageable pageable);

    Page<BookEntity> searchByTitle(String title, Pageable pageable);

    void removeByISBN(String isbn);

}

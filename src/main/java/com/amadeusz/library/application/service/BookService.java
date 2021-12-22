package com.amadeusz.library.application.service;

import com.amadeusz.library.application.model.book.Book;
import com.amadeusz.library.infrastructure.repository.entities.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Book add(Book book);

    BookEntity getByISBN(String isbn);

    Book updateBook(String isbn, Book book);

    Page<Book> getAllBooks(Pageable pageable);

    Page<Book> searchByYear(int year, Pageable pageable);

    Page<Book> searchByAuthorName(String authorName, Pageable pageable);

    Page<Book> searchByCategory(String category, Pageable pageable);

    Page<Book> searchByTitle(String title, Pageable pageable);

    void removeByISBN(String isbn);

}

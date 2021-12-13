package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.infrastructure.model.BookEntity;

import java.util.List;

public interface BookService {

    BookEntity add(Book book);

    BookEntity getByISBN(String isbn);

    BookEntity updateBookTitle(String title, String isbn);

    List<BookEntity> getAllBooks();

    List<BookEntity> searchByYear(int year);

    List<BookEntity> searchByAuthorName(String authorName);

    List<BookEntity> searchByCategory(String category);

    List<BookEntity> searchByTitle(String title);
}

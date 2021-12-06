package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.infrastructure.model.BookEntity;

import java.util.List;

public interface BookService {

    BookEntity add(BookEntity book);

    BookEntity getByISBN(String isbn);

    BookEntity updateBookData(BookEntity book);

    BookEntity updateBookTitle(String title, String isbn);

    List<BookEntity> getAllBooks();

    List<BookEntity> searchByYear(int year);

    List<BookEntity> searchByAuthorName(String authorName);

    List<BookEntity> searchByCategory(String category);

    List<BookEntity> searchByTitle(String title);
}

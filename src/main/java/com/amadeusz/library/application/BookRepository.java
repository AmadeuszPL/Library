package com.amadeusz.library.application;

import com.amadeusz.library.infrastructure.BookEntity;

import java.util.List;

public interface BookRepository {

    void create(Book book);

    List<Book> read();

    Book readByIsbn(String isbn);

    void update(Book book);

    void delete(Book book);

    List<Book> searchByYear(int year);

    List<Book> searchByAuthorName(String authorName);

    List<Book> searchByCategory(String category);

    List<Book> searchByTitle(String title);
}

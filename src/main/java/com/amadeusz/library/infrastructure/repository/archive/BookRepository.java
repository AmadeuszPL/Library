package com.amadeusz.library.infrastructure.repository.archive;

import com.amadeusz.library.application.model.book.Book;

import java.util.List;

public interface BookRepository {

    void create(Book book);

    void update(Book book);

    void delete(Book book);

    List<Book> searchByYear(int year);

    List<Book> searchByAuthorName(String authorName);

    List<Book> searchByCategory(String category);

    List<Book> searchByTitle(String title);
}

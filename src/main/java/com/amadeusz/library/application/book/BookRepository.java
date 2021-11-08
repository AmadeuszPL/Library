package com.amadeusz.library.application.book;

import com.amadeusz.library.application.book.Book;

import java.util.List;

public interface BookRepository {

    void create(Book book);

    Book readByIsbn(String isbn);

    void update(Book book);

    void delete(Book book);

    List<Book> searchByYear(int year);

    List<Book> searchByAuthorName(String authorName);

    List<Book> searchByCategory(String category);

    List<Book> searchByTitle(String title);
}

package com.amadeusz.library.application;

import java.util.List;

public interface BookServiceInterface {

    void add(Book book);

    Book getByISBN(String isbn);

    List<Book> searchByYear(int year);

    List<Book> searchByAuthorName(String authorName);

    List<Book> searchByCategory(String category);

    List<Book> searchByTitle(String title);
}

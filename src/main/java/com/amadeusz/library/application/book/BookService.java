package com.amadeusz.library.application.book;

import com.amadeusz.library.application.book.Book;

import java.util.List;

public interface BookService {

    void add(Book book);

    Book getByISBN(String isbn);

    void updateBookData(Book book);

    void updateBookTitle(String title, String isbn);

    List<Book> searchByYear(int year);

    List<Book> searchByAuthorName(String authorName);

    List<Book> searchByCategory(String category);

    List<Book> searchByTitle(String title);
}

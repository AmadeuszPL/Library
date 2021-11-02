package com.amadeusz.library.application;

import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void add(Book book) {
        bookRepository.create(book);
    }

    public Book getByISBN(String isbn) {
        return bookRepository.readByIsbn(isbn);
    }

    public void updateBookData(Book book){
        bookRepository.update(book);
    }

    public List<Book> searchByYear(int year) {
        return bookRepository.searchByYear(year);
    }

    public List<Book> searchByAuthorName(String authorName) {
        return bookRepository.searchByAuthorName(authorName);
    }

    public List<Book> searchByCategory(String category) {
        return bookRepository.searchByCategory(category);
    }

    public List<Book> searchByTitle(String title) {
        return bookRepository.searchByTitle(title);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookRepository=" + bookRepository +
                '}';
    }
}

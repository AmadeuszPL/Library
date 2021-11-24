package com.amadeusz.library.application.book;

import com.amadeusz.library.application.book.BookRepository;
import com.amadeusz.library.application.book.BookService;
import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.application.book.BookByYearProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bookService")
public class DefaultBookService implements BookService, BookByYearProvider {

    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository bookRepository) {
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

    public void updateBookTitle(String title, String isbn) {
        Book book = bookRepository.readByIsbn(isbn);
        book.updateTitle(title);
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

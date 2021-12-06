package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.application.exceptions.IllegalOperationException;
import com.amadeusz.library.infrastructure.model.BookEntity;
import com.amadeusz.library.infrastructure.model.mappers.BookEntityMapper;
import com.amadeusz.library.infrastructure.model.mappers.DefaultBookEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;


public class InMemoryBookRepository implements BookRepository {

    private final BookEntityMapper mapper;
    private final Map<String, BookEntity> bookCatalog;

    public InMemoryBookRepository() {
        this.bookCatalog = new HashMap<>();
        this.mapper = new DefaultBookEntityMapper();

    }

    @Override
    public void create(Book book) {
        bookCatalog.put(book.getIsbn().getValue(), mapper.map(book));
    }

    @Override
    public Book readByIsbn(String isbn) {
        Optional<BookEntity> bookEntity = Optional.ofNullable(bookCatalog.get(isbn));
        if (bookEntity.isEmpty()) {
            throw new IllegalOperationException("Book with this ISBN not in " +
                    "Book Repository");
        }
        return mapper.map(bookEntity.get());
    }

    @Override
    public void update(Book book) {
        String bookIsbn = book.getIsbn().getValue();
        bookCatalog.computeIfPresent(bookIsbn, (k, v) -> mapper.map(book));
    }

    @Override
    public void delete(Book book) {
        bookCatalog.remove(book.getIsbn().getValue());
    }

    @Override
    public List<Book> searchByYear(int year) {
        List<Book> booksByYear = new ArrayList<>();
        for (BookEntity bookEntity : bookCatalog.values()) {
            if (bookEntity.getPublicationYear() == year) {
                booksByYear.add(mapper.map(bookEntity));
            }
        }
        return booksByYear;
    }

    @Override
    public List<Book> searchByAuthorName(String authorName) {
        return bookCatalog.values().stream()
                .filter(bookEntity -> bookEntity.getAuthorName().equals(authorName))
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByCategory(String category) {
        return bookCatalog.values().stream()
                .filter(bookEntity -> bookEntity.getCategory().equals(category))
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> searchByTitle(String title) {
        List<Book> booksByTitle = new ArrayList<>();
        for (BookEntity bookEntity : bookCatalog.values()) {
            if (bookEntity.getTitle().equals(title)) {
                booksByTitle.add(mapper.map(bookEntity));
            }
        }
        return booksByTitle;
    }

    @Override
    public String toString() {
        return "InMemoryBookRepository{" +
                ", bookCatalog=" + bookCatalog +
                '}';
    }
}

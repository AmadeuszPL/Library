package com.amadeusz.library.application.service;

import com.amadeusz.library.application.exceptions.NoInRepositoryException;
import com.amadeusz.library.infrastructure.repository.entities.BookEntity;
import com.amadeusz.library.infrastructure.repository.BookJpaRepository;
import com.amadeusz.library.application.model.book.Book;
import com.amadeusz.library.infrastructure.repository.entities.mappers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("bookService")
public class BookServiceImpl implements BookService {

    private final BookJpaRepository bookRepository;

    @Override
    public Book add(Book book) {
        BookEntity bookEntity = DefaultBookEntityMapper.map(book);
        BookEntity savedEntity = bookRepository.saveAndFlush(bookEntity);
        return DefaultBookEntityMapper.map(savedEntity);
    }

    @Override
    public Book getByISBN(String isbn) {
        Optional<BookEntity> bookEntity = bookRepository.findById(isbn.replaceAll("[^0-9]", ""));
        if (bookEntity.isEmpty()) {
            throw new NoInRepositoryException("Book of this ISBN is not in repository");
        }
        return DefaultBookEntityMapper.map(bookEntity.get());
    }

    @Override
    public Book updateBook(String isbn, Book book) {
        getByISBN(isbn);
        BookEntity bookEntity = bookRepository.saveAndFlush(DefaultBookEntityMapper.map(book));
        return DefaultBookEntityMapper.map(bookEntity);
    }

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findAll(pageable);
        return bookEntities.map(DefaultBookEntityMapper::map);
    }

    @Override
    public Page<Book> searchByYear(int publicationYear, Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findByPublicationYear(publicationYear, pageable);
        return bookEntities.map(DefaultBookEntityMapper::map);
    }

    @Override
    public Page<Book> searchByAuthorName(String authorName, Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findByAuthorName(authorName, pageable);
        return bookEntities.map(DefaultBookEntityMapper::map);
    }

    @Override
    public Page<Book> searchByCategory(String category, Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findByCategory(category.toUpperCase(), pageable);
        return bookEntities.map(DefaultBookEntityMapper::map);
    }

    @Override
    public Page<Book> searchByTitle(String title, Pageable pageable) {
        Page<BookEntity> bookEntities = bookRepository.findByTitleLike(title, pageable);
        return bookEntities.map(DefaultBookEntityMapper::map);
    }

    @Override
    public void removeByISBN(String isbn) {
        bookRepository.deleteByIsbn(isbn.replaceAll("[^0-9]", ""));
    }

    @Override
    public void removeAll() {
        bookRepository.deleteAll();
    }

}

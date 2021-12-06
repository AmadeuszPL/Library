package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.application.bookitem.BookItem;
import com.amadeusz.library.application.exceptions.NoBookInRepositoryException;
import com.amadeusz.library.infrastructure.model.BookEntity;
import com.amadeusz.library.infrastructure.model.mappers.BookEntityMapper;
import com.amadeusz.library.infrastructure.model.mappers.DefaultBookEntityMapper;
import com.amadeusz.library.infrastructure.repository.BookJpaRepository;
import com.amadeusz.library.application.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("bookService")
public class DefaultBookService implements BookService {

    @Autowired
    private BookJpaRepository bookRepository;

    //    public DefaultBookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @Override
    public BookEntity add(BookEntity book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public BookEntity getByISBN(String isbn) {
        Optional<BookEntity> byId = bookRepository.findById(isbn);
        if(byId.isEmpty()){
            throw new NoBookInRepositoryException("Book of this ISBN is not in repository");
        }
        return byId.get();
    }

    @Override
    public BookEntity updateBookData(BookEntity book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public BookEntity updateBookTitle(String title, String isbn) {
        Optional<BookEntity> byId = bookRepository.findById(isbn);
        if (byId.isEmpty()){
            throw new NoBookInRepositoryException("Book of this ISBN not in repository");
        }
        BookEntity entity = byId.get();
        entity.setTitle(title);
        return bookRepository.saveAndFlush(entity);
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookEntity> searchByYear(int year) {
        return bookRepository.findByPublicationYear(year);
    }

    @Override
    public List<BookEntity> searchByAuthorName(String authorName) {
        return bookRepository.findByAuthorName(authorName);
    }

    @Override
    public List<BookEntity> searchByCategory(String category) {
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<BookEntity> searchByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookRepository=" + bookRepository +
                '}';
    }
}

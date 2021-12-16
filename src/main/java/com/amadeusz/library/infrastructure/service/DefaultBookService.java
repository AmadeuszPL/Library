package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.application.exceptions.NoBookInRepositoryException;
import com.amadeusz.library.infrastructure.model.BookEntity;
import com.amadeusz.library.infrastructure.model.mappers.BookEntityMapper;
import com.amadeusz.library.infrastructure.model.mappers.DefaultBookEntityMapper;
import com.amadeusz.library.infrastructure.repository.BookJpaRepository;
import com.amadeusz.library.application.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    private BookEntityMapper mapper = new DefaultBookEntityMapper();

    @Override
    public Book add(Book book) {
/*        if(true){
            throw new IllegalArgumentException("Exception");
        }*/
        BookEntity bookEntity = mapper.map(book);
        BookEntity bookEntity1 = bookRepository.saveAndFlush(bookEntity);
        return mapper.map(bookEntity1);
    }

    @Override
    public BookEntity getByISBN(String isbn) {
        Optional<BookEntity> byId = bookRepository.findById(isbn.replaceAll("[^0-9]", ""));
        if(byId.isEmpty()){
            throw new NoBookInRepositoryException("Book of this ISBN is not in repository");
        }
        return byId.get();
    }

//    @Override
//    public BookEntity updateBookData(BookEntity book) {
//        return bookRepository.saveAndFlush(book);
//    }

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
    public Page<BookEntity> getAllBooks(Pageable pageable) {
/*        if(true){
            throw new RuntimeException("mus to super programista");
        }*/
        return bookRepository.findAll(pageable);
    }

    @Override
    public Page<BookEntity> searchByYear(int publicationYear, Pageable pageable) {
        return bookRepository.findByPublicationYear(publicationYear, pageable);
    }

    @Override
    public Page<BookEntity> searchByAuthorName(String authorName, Pageable pageable) {
        return bookRepository.findByAuthorName(authorName, pageable);
    }

    @Override
    public Page<BookEntity> searchByCategory(String category, Pageable pageable) {
        return bookRepository.findByCategory(category.toUpperCase(), pageable);
    }

    @Override
    public Page<BookEntity> searchByTitle(String title, Pageable pageable) {
//        Pageable pageWithThreeElements = PageRequest.ofSize(3);
        return bookRepository.findByTitleLike(title, pageable);
    }

    @Override
    public String toString() {
        return "BookService{" +
                "bookRepository=" + bookRepository +
                '}';
    }
}

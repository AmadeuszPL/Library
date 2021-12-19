package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.exceptions.NoBookInRepositoryException;
import com.amadeusz.library.infrastructure.model.BookEntity;
import com.amadeusz.library.infrastructure.model.mappers.BookEntityMapper;
import com.amadeusz.library.infrastructure.model.mappers.DefaultBookEntityMapper;
import com.amadeusz.library.infrastructure.repository.BookJpaRepository;
import com.amadeusz.library.application.book.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("bookService")
public class DefaultBookService implements BookService {

    @Autowired
    private BookJpaRepository bookRepository;

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
        if (byId.isEmpty()) {
            throw new NoBookInRepositoryException("Book of this ISBN is not in repository");
        }
        return byId.get();
    }

    @Override
    public ResponseEntity<Book> updateBook(String isbn, Book book) {
        try {
            Optional<BookEntity> bookEntity = bookRepository.findById(isbn.replaceAll("[^0-9]", ""));
            if (bookEntity.isEmpty()) {
                throw new CustomerNotFoundException("Update failed, customer not found");
            } else {
                BookEntity patchedBook = mapper.map(book);
                bookRepository.saveAndFlush(patchedBook);
                return ResponseEntity.ok(book);
            }
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @Override
    public Page<BookEntity> getAllBooks(Pageable pageable) {
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
    @Transactional
    public void removeByISBN(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    @Override
    public String toString() {
        return "BookService{" + "bookRepository=" + bookRepository + '}';
    }

}

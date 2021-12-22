package com.amadeusz.library.application.service;

import com.amadeusz.library.infrastructure.repository.archive.BookRepository;
import com.amadeusz.library.application.model.bookitem.BookItem;
import com.amadeusz.library.infrastructure.repository.BookItemRepository;

import java.util.*;

//@Service("bookItemService")
public class DefaultBookItemService implements BookItemService {

    private final BookItemRepository bookItemRepository;
    private final BookRepository bookRepository;

    public DefaultBookItemService(BookItemRepository bookItemRepository,
                                  BookRepository bookRepository) {
        this.bookItemRepository = bookItemRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBookItem(BookItem bookitem) {
        bookRepository.readByIsbn(bookitem.getBookIsbn());
        bookItemRepository.create(bookitem);
    }

    public BookItem searchBookItemById(UUID bookId) {
        return bookItemRepository.readByBookItemId(bookId);
    }

    @Override
    public void deleteBookItem(BookItem bookItem) {
        bookItemRepository.delete(bookItem);
    }

    @Override
    public String toString() {
        return "BookRepositoryService{" +
                "bookRepository=" + bookItemRepository +
                '}';
    }
}

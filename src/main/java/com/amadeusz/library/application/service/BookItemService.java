package com.amadeusz.library.application.service;

import com.amadeusz.library.application.model.book.Book;
import com.amadeusz.library.application.model.bookitem.BookItem;
import com.amadeusz.library.infrastructure.repository.entities.BookEntity;
import com.amadeusz.library.infrastructure.repository.entities.BookItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookItemService {

    BookItem add(BookItem bookItem);

    Page<BookItem> getByIsbn(String bookIsbn, Pageable pageable);

    BookItem getById(UUID id);

    void removeById(UUID id);

    BookItem updateBook(BookItem bookItem);

    List<BookItem> readAvailableByIsbn(String isbn);

    List<BookItem> readLoanedByIsbn(String isbn);

    void removeAll();

}

package com.amadeusz.library.application.service;

import com.amadeusz.library.application.exceptions.NoInRepositoryException;
import com.amadeusz.library.infrastructure.repository.BookItemJpaRepository;
import com.amadeusz.library.application.model.bookitem.BookItem;
import com.amadeusz.library.infrastructure.repository.entities.BookItemEntity;
import com.amadeusz.library.infrastructure.repository.entities.mappers.BookItemEntityMapper;
import com.amadeusz.library.infrastructure.repository.entities.mappers.DefaultBookItemEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("bookItemService")
public class BookItemServiceImpl implements BookItemService {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookItemJpaRepository bookItemRepository;

    private final BookItemEntityMapper mapper = new DefaultBookItemEntityMapper();

    @Override
    public BookItem add(BookItem bookItem) {
        bookService.getByISBN(bookItem.getBookIsbn());
        BookItemEntity bookItemEntity = mapper.map(bookItem);
        BookItemEntity bookItemEntity1 = bookItemRepository.saveAndFlush(bookItemEntity);
        return mapper.map(bookItemEntity1);
    }

    @Override
    public Page<BookItem> getByIsbn(String bookIsbn, Pageable pageable) {
        Page<BookItemEntity> byIsbn =
                bookItemRepository
                        .findByBookIsbn(bookIsbn.replaceAll("[^0-9]", ""), pageable);
        return byIsbn.map(mapper::map);
    }

    @Override
    public BookItem getById(UUID id) {
        Optional<BookItemEntity> bookItemEntity = bookItemRepository.findById(id);
        if (bookItemEntity.isEmpty()) {
            throw new NoInRepositoryException("Book item not added to repository");
        }
        return mapper.map(bookItemEntity.get());
    }

    @Override
    public void removeById(UUID id) {
        bookItemRepository.deleteById(id);
    }

    @Override
    public BookItem updateBook(BookItem bookItem) {
        getById(bookItem.getId());
        BookItemEntity bookItemEntity = bookItemRepository.saveAndFlush(mapper.map(bookItem));
        return mapper.map(bookItemEntity);
    }

    @Override
    public List<BookItem> readAvailableByIsbn(String isbn) {
        List<BookItemEntity> available = bookItemRepository.findByBookIsbnAndBookItemStatus(isbn, "AVAILABLE");
        return available.stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public List<BookItem> readLoanedByIsbn(String isbn) {
        List<BookItemEntity> loaned = bookItemRepository.findByBookIsbnAndBookItemStatus(isbn, "LOANED");
        return loaned.stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public void removeAll(){
        bookItemRepository.deleteAll();
    }

}

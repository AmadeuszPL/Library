package com.amadeusz.library.infrastructure.bookitem;

import com.amadeusz.library.application.bookitem.BookItem;
import com.amadeusz.library.application.bookitem.BookItemRepository;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryBookItemRepository implements BookItemRepository {

    private final BookItemEntityMapper mapper;
    private final Map<UUID, BookItemEntity> bookRepository;

    public InMemoryBookItemRepository() {
        this.mapper = new DefaultBookItemEntityMapper();
        this.bookRepository = new HashMap<>();
    }

    @Override
    public void create(BookItem bookitem) {
        bookRepository.put(bookitem.getId(), mapper.map(bookitem));
    }

    @Override
    public BookItem readByBookItemId(UUID bookItemId) {
        Optional<BookItemEntity> bookItemEntity =
                Optional.ofNullable(bookRepository.get(bookItemId));

        if (bookItemEntity.isEmpty())
            throw new NoBookInRepositoryException("No Book in Repository");
        else {
            return bookItemEntity
                    .map(mapper::map)
                    .get();
        }
    }

    @Override
    public List<BookItem> readByIsbn(String isbn) {
        return bookRepository.values().stream()
                .filter(bookItemEntity -> bookItemEntity.getBookIsbn().equals(isbn))
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookItem> readAvailableByIsbn(String isbn) {
        return bookRepository.values().stream()
                .filter(bookItemEntity -> bookItemEntity.getBookIsbn().equals(isbn))
                .filter(bookItemEntity -> bookItemEntity
                        .getBookItemStatus().equals(BookItem.BookItemStatus.AVAILABLE.name()))
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookItem> readLoanedByIsbn(String isbn) {
        return bookRepository.values().stream()
                .filter(bookItemEntity -> bookItemEntity.getBookIsbn().equals(isbn))
                .filter(bookItemEntity -> bookItemEntity
                        .getBookItemStatus().equals(BookItem.BookItemStatus.LOANED.name()))
                .map(mapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void update(BookItem bookItem) {
        bookRepository.computeIfPresent(bookItem.getId(),
                (key, value) -> mapper.map(bookItem));

    }

    @Override
    public void delete(BookItem bookItem) {
        bookRepository.remove(bookItem.getId());
    }

    @Override
    public String toString() {
        return "InMemoryBookItemRepository{" +
                "bookRepository=" + bookRepository +
                '}';
    }
}

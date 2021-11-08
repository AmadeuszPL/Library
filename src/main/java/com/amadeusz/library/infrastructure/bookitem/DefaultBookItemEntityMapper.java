package com.amadeusz.library.infrastructure.bookitem;

import com.amadeusz.library.application.bookitem.BookItem;

public class DefaultBookItemEntityMapper implements BookItemEntityMapper {

    @Override
    public BookItemEntity map(BookItem bookItem) {
        BookItemEntity bookItemEntity = new BookItemEntity();
        bookItemEntity.setBookIsbn(bookItem.getBookIsbn());
        bookItemEntity.setId(bookItem.getId());
        bookItemEntity.setRackNumber(bookItem.getRackNumber().getLocationCode());
        bookItemEntity.setBookItemStatus(bookItem.getBookItemStatus().name());
        return bookItemEntity;
    }

    @Override
    public BookItem map(BookItemEntity bookItemEntity){
        return new BookItem(bookItemEntity.getId(),
                bookItemEntity.getBookIsbn(), bookItemEntity.getRackNumber(),
                bookItemEntity.getBookItemStatus());
    }
}

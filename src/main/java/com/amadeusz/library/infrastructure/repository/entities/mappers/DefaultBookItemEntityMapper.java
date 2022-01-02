package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.bookitem.BookItem;
import com.amadeusz.library.application.model.bookitem.RackNumber;
import com.amadeusz.library.infrastructure.repository.entities.BookItemEntity;

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
                bookItemEntity.getBookIsbn(), new RackNumber(bookItemEntity.getRackNumber()),
                BookItem.BookItemStatus.valueOf(bookItemEntity.getBookItemStatus())
                );
    }
}

package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.BookItem;

public class DefaultBookItemEntityMapper implements BookItemEntityMapper {

    @Override
    public BookItemEntity map(BookItem bookItem) {

        BookItemEntity bookItemEntity = new BookItemEntity();
        bookItemEntity.setBookIsbn(bookItem.getBookIsbn());
        bookItemEntity.setId(bookItem.getId());
        return bookItemEntity;

    }

    @Override
    public BookItem map(BookItemEntity bookItemEntity) throws Exception {

        return new BookItem(bookItemEntity.getId(),
                bookItemEntity.getBookIsbn(),
                bookItemEntity.getRackNumber());

    }

}

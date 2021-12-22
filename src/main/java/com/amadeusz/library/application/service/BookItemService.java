package com.amadeusz.library.application.service;

import com.amadeusz.library.application.model.bookitem.BookItem;

import java.util.UUID;

public interface BookItemService {

    void addBookItem(BookItem bookitem);

    BookItem searchBookItemById(UUID bookId) throws Exception;

    void deleteBookItem(BookItem bookItem);

}

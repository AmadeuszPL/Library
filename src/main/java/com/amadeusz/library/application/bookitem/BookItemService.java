package com.amadeusz.library.application.bookitem;

import java.util.UUID;

public interface BookItemService {

    void addBookItem(BookItem bookitem);

    BookItem searchBookItemById(UUID bookId) throws Exception;

    void deleteBookItem(BookItem bookItem);

}

package com.amadeusz.library.application.bookitem;

import java.util.List;
import java.util.UUID;

public interface BookItemRepository {

    void create(BookItem bookitem);

    BookItem readByBookItemId(UUID bookItemId);

    List<BookItem> readByIsbn(String isbn);

    List<BookItem> readAvailableByIsbn(String isbn);

    List<BookItem> readLoanedByIsbn(String isbn);

    void update(BookItem bookItem);

    void delete(BookItem bookItem);

}
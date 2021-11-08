package com.amadeusz.library.infrastructure.bookitem;

import com.amadeusz.library.application.bookitem.BookItem;

public interface BookItemEntityMapper {

    BookItemEntity map(BookItem bookItem);
    BookItem map(BookItemEntity bookItemEntity);

}

package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.BookItem;

public interface BookItemEntityMapper {

    BookItemEntity map(BookItem bookItem);
    BookItem map(BookItemEntity bookItemEntity) throws Exception;

}

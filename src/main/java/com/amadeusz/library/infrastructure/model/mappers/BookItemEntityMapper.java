package com.amadeusz.library.infrastructure.model.mappers;

import com.amadeusz.library.application.bookitem.BookItem;
import com.amadeusz.library.infrastructure.model.BookItemEntity;

public interface BookItemEntityMapper {

    BookItemEntity map(BookItem bookItem);
    BookItem map(BookItemEntity bookItemEntity);

}

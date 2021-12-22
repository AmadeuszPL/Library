package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.bookitem.BookItem;
import com.amadeusz.library.infrastructure.repository.entities.BookItemEntity;

public interface BookItemEntityMapper {

    BookItemEntity map(BookItem bookItem);
    BookItem map(BookItemEntity bookItemEntity);

}

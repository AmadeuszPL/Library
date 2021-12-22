package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.book.Book;
import com.amadeusz.library.infrastructure.repository.entities.BookEntity;

public interface BookEntityMapper {

    BookEntity map(Book book);
    Book map(BookEntity bookEntity);

}

package com.amadeusz.library.infrastructure.model.mappers;

import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.infrastructure.model.BookEntity;

public interface BookEntityMapper {

    BookEntity map(Book book);
    Book map(BookEntity bookEntity);

}

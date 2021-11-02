package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.Book;

public interface BookEntityMapper {

    BookEntity map(Book book);
    Book map(BookEntity bookEntity);

}

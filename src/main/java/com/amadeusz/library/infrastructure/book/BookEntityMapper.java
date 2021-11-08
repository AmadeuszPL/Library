package com.amadeusz.library.infrastructure.book;

import com.amadeusz.library.application.book.Book;

public interface BookEntityMapper {

    BookEntity map(Book book);
    Book map(BookEntity bookEntity);

}

package com.amadeusz.library.application.book;

import com.amadeusz.library.application.book.Book;

import java.util.List;

public interface BookByYearProvider {

    List<Book> searchByYear(int year);

}

package com.amadeusz.library.application;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookItemRepository {
    
    int getBooksCount();

    void save(BookItem bookitem);

    void removeById(UUID bookId);

    BookItem get(UUID bookId);

/*    List<BookItem> searchByCategory(Book.SubjectCategory subjectCategory);

    List<BookItem> searchByTitle(String title);

    List<BookItem> searchByYear(int year);

    List<BookItem> searchByAuthor(String author); */

}

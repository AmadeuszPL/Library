package com.amadeusz.libraryfun;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookRepository {

    void addBook(BookItem bookitem);

    void deleteBookUsingId(UUID id);

    Map<UUID, BookItem> searchByTitle(String title);

    Map<UUID, BookItem> searchByCategory(Book.SubjectCategory subjectCategory);

    Map<UUID, BookItem> searchByAuthor(String author);

    Map<UUID, BookItem> searchByYear(int year);

    Optional<BookItem> searchById(UUID bookId);

}

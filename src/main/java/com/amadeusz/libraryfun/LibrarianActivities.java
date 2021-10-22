package com.amadeusz.libraryfun;

import java.util.Map;

public interface LibrarianActivities {

    public boolean addBook(InMemoryBookRepository repository, BookItem book);

    public Person checkWhoIssuedBook(Map<String, BookItem> db,
                                            String ISBN);

    public void printUserIssuedBooks(Map<String, BookItem> db,
                                     LibraryMember libraryMember);
}

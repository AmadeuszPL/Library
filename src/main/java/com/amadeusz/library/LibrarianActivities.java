package com.amadeusz.library;

import java.util.Map;

interface LibrarianActivities {

    boolean addBook(InMemoryBookRepository repository, BookItem book);

    Person checkWhoIssuedBook(Map<String, BookItem> db,
                                     String ISBN);

    void printUserIssuedBooks(Map<String, BookItem> db,
                                     LibraryMember libraryMember);
}

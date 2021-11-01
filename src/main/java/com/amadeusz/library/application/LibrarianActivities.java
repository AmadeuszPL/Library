package com.amadeusz.library.application;

import com.amadeusz.library.infrastructure.InMemoryBookRepository;

import java.util.Map;

interface LibrarianActivities {

    boolean addBook(InMemoryBookRepository repository, BookItem book);

    Person checkWhoIssuedBook(Map<String, BookItem> db,
                                     String ISBN);

    void printUserIssuedBooks(Map<String, BookItem> db,
                                     LibraryMember libraryMember);
}

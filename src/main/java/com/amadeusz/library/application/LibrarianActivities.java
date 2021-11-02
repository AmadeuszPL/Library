package com.amadeusz.library.application;

import com.amadeusz.library.infrastructure.InMemoryBookItemRepository;

import java.util.Map;

interface LibrarianActivities {

    boolean addBook(InMemoryBookItemRepository repository, BookItem book);

    Person checkWhoIssuedBook(Map<String, BookItem> db,
                                     String ISBN);

    void printUserIssuedBooks(Map<String, BookItem> db,
                                     LibraryMember libraryMember);
}

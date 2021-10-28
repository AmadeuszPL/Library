package com.amadeusz.library;

import java.util.Map;

public class Librarian extends Account implements LibrarianActivities {

    public Librarian(String login, String password, Person person) {
        super(login, password, person);
    }

    @Override
    public boolean addBook(InMemoryBookRepository repository, BookItem book) {
        return false;
    }

    @Override
    public Person checkWhoIssuedBook(Map<String, BookItem> db, String ISBN) {
        return null;
    }

    @Override
    public void printUserIssuedBooks(Map<String, BookItem> db,
                                     LibraryMember libraryMember) { }

}


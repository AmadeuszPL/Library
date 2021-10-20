package com.amadeusz.libraryfun;

import java.util.Map;

public class Librarian extends Account implements LibrarianActivities{

    public Librarian(String login, String password, Person person) {
        super(login, password, person);
    }

    @Override
    public void addBook(Map<String, BookItem> db, BookItem book) {
        db.put(book.getISBN(), book);
    }


}

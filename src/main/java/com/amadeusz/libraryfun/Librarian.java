package com.amadeusz.libraryfun;

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

/*    @Override
    public Person checkWhoIssuedBook(Map<String, BookItem> db,
                                            String ISBN) {
        BookItem book = db.get(ISBN);
        Account userAccount = book.getIssuer();
        if (userAccount == null){
            return null;
        }
        return userAccount.getPerson();
    }*/

    @Override
    public void printUserIssuedBooks(Map<String, BookItem> db, LibraryMember libraryMember) {

    }

 /*   @Override
    public void printUserIssuedBooks(Map<String, BookItem> db,
                                     LibraryMember libraryMember) {
        db.entrySet().stream()
                .filter(BookItem->BookItem.getValue().getIssuer() != null)
                .filter(BookItem->BookItem.getValue().getIssuer().getPerson().equals(libraryMember.getPerson()))
                .map(BookItem-> (BookItem.getKey() + " " + BookItem.getValue().getTitle() + " " + BookItem.getValue().getBookStatus()))
                .forEach(System.out::println);

        /*for(BookItem item : db.values()){
            if (item.getIssuer() != null && item.getIssuer().getPerson().equals(libraryMember.getPerson()))
                System.out.println(item.getISBN() + " " + item.getTitle());
        }*/
}


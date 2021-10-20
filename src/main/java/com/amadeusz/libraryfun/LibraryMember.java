package com.amadeusz.libraryfun;

import java.time.LocalDate;
import java.util.Map;

public class LibraryMember extends  Account implements LibraryMemberActivities{

    public LibraryMember(String login, String password, Person person) {
        super(login, password, person);
    }

    @Override
    public void checkOutBookByISBN(Map<String, BookItem> db, String ISBN) {
        BookItem book = db.get(ISBN);
        LocalDate date = book.getIssueDate();
        if (date == null || (book.getIssuer().equals(this) && book.getBookStatus().equals(BookStatus.RESERVED))){
            book.setIssueDate();
            book.setDueDateLoan();
            book.setBookStatus(BookStatus.LOANED);
            book.setIssuer(this);
        } else {
            if (book.getIssuer().equals(this)){
                System.out.println("You've already loaned this book.");
            } else {
                System.out.println(book.getBookStatus());
            }
        }
    }

    @Override
    public void reserveBookByISBN(Map<String, BookItem> db, String ISBN) {
        BookItem book = db.get(ISBN);
        LocalDate date = book.getIssueDate();
        if (date == null) {
            book.setIssueDate();
            book.setDueDateReserve();
            book.setBookStatus(BookStatus.RESERVED);
            book.setIssuer(this);
        } else {
            if (book.getIssuer().equals(this) && book.getBookStatus().equals(BookStatus.LOANED)) {
                System.out.println("You've already loaned this book.");
            } else {
                System.out.println("You've already reserved this book.");
            }
        }
    }
}

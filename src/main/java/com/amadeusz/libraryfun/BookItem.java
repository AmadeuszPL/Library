package com.amadeusz.libraryfun;

import java.time.LocalDate;

public class BookItem extends Book {
    private RackNumber rackNumber;
    private BookStatus bookStatus;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private Account issuer;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate() {
        this.issueDate = LocalDate.now();
    }

    public void setDueDateLoan() {
        this.dueDate = LocalDate.now().plusDays(30);
    }

    public void setDueDateReserve() {
        this.dueDate = LocalDate.now().plusDays(10);
    }

    public Account getIssuer() {
        return issuer;
    }

    public void setIssuer(Account issuer) {
        this.issuer = issuer;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public BookItem(String ISBN, String title, int publicationYear,
                    Author author, SubjectCategory category, RackNumber rackNumber) {
        super(ISBN, title, publicationYear, author, category);
        this.rackNumber = rackNumber;
    }

    //this.borrowed = LocalDate.now();
    //this.dueDate = borrowed.plusDays(30);

    public RackNumber getRackNumber() {
        return rackNumber;
    }

    public void checkOut(){
        System.out.println(bookStatus);
    }
}

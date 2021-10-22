package com.amadeusz.libraryfun;

import java.time.LocalDate;
import java.util.Objects;

public class BookItem extends Book {
    private Double barcode;
    private RackNumber rackNumber;
    private BookStatus bookStatus;
    private LocalDate issueDate;

    public Double getBarcode() {
        return barcode;
    }

    private LocalDate dueDate;
    private Account issuer;

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate() {
        this.issueDate = LocalDate.now();
    }

    @Override
    public Account getIssuer() {
        return issuer;
    }

    public void setDueDateLoan() {
        this.dueDate = LocalDate.now().plusDays(10);
    }

    public void setDueDateReserve() {
        this.dueDate = LocalDate.now().plusDays(5);
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

    public BookItem(Double barcode, String ISBN, String title,
                    int publicationYear,
                    Author author, SubjectCategory category, RackNumber rackNumber) {
        super(ISBN, title, publicationYear, author, category);
        this.barcode = barcode;
        this.rackNumber = rackNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItem bookItem = (BookItem) o;
        return barcode.equals(bookItem.barcode) && rackNumber.equals(bookItem.rackNumber) && bookStatus == bookItem.bookStatus && issueDate.equals(bookItem.issueDate) && dueDate.equals(bookItem.dueDate) && issuer.equals(bookItem.issuer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, rackNumber, bookStatus, issueDate, dueDate, issuer);
    }

    @Override
    public String toString() {
        return "BookItem{" + super.getTitle() +
                "barcode=" + barcode + "\n" +
                '}';
    }

    //this.borrowed = LocalDate.now();
    //this.dueDate = borrowed.plusDays(30);

/*    public RackNumber getRackNumber() {
        return rackNumber;
    }

    public void checkOut(){
        System.out.println(bookStatus);
    }
*/

}

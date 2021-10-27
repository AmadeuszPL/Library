package com.amadeusz.libraryfun;

import java.time.LocalDate;
import java.util.UUID;

class BookIssue {

    private final UUID bookId;
    private BookStatus bookStatus;
    private UUID issuer;
    private LocalDate issueDate;

    public BookIssue(UUID bookId, BookStatus bookStatus) {

        this.bookId = bookId;
        this.bookStatus = bookStatus;
        this.issueDate = LocalDate.now();

    }

    public UUID getBookId() {
        return bookId;
    }

    public UUID getIssuer() {
        return issuer;
    }

    public void setIssuer(UUID issuer) {
        this.issuer = issuer;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    enum BookStatus {

        RESERVED,
        LOANED,
        AVAILABLE

    }

    @Override
    public String toString() {
        return "book id: " + bookId + " status: " + bookStatus + " issuer: " + issuer +
                "\n";
    }
}

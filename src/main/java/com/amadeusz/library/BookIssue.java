package com.amadeusz.library;

import java.time.LocalDate;
import java.util.UUID;

class BookIssue {

    private final UUID bookId;
    private BookStatus bookStatus;
    private ReservationOfLoanedBookStatus reservationStatus;
    private UUID issuer;
    private UUID booker;
    private LocalDate issueDate;

    public BookIssue(UUID bookId, BookStatus bookStatus) {

        this.bookId = bookId;
        this.bookStatus = bookStatus;
        this.issueDate = LocalDate.now();

    }

    static BookIssue reserve(UUID bookId) {
        return new BookIssue(bookId, BookStatus.RESERVED);
    }

    static BookIssue loan(UUID bookId) {
        return new BookIssue(bookId, BookStatus.LOANED);
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

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public ReservationOfLoanedBookStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationOfLoanedBookStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public UUID getBooker() {
        return booker;
    }

    public void setBooker(UUID booker) {
        this.booker = booker;
    }

    enum BookStatus {

        RESERVED,
        LOANED,
        AVAILABLE

    }

    enum ReservationOfLoanedBookStatus {

        RESERVED,
        NONE,

    }

    @Override
    public String toString() {
        String buker = "";
        if (booker != null){
            buker =
                    "\n  booker: " + booker + " bookerStatus: " + reservationStatus + "\n *****";
        }
        return "book id: " + bookId + " status: " + bookStatus + " issuer: " + issuer +
                buker +
                "\n";
    }
}

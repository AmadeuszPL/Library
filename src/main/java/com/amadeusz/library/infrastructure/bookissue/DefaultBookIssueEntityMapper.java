package com.amadeusz.library.infrastructure.bookissue;

import com.amadeusz.library.application.bookissue.BookLending;
import com.amadeusz.library.application.bookissue.BookReservation;

public class DefaultBookIssueEntityMapper implements BookIssueMapper {

    @Override
    public BookLendingEntity map(BookLending bookLending) {
        BookLendingEntity bookLendingEntity = new BookLendingEntity();
        bookLendingEntity.setBookItemId(bookLending.getBookItemId());
        bookLendingEntity.setIssueDate(bookLending.getIssueDate());
        bookLendingEntity.setIssueId(bookLending.getIssueId());
        bookLendingEntity.setIssuerId(bookLending.getIssuerId());
        bookLendingEntity.setLendingStatus(bookLending.getLendingStatus().name());
        bookLendingEntity.setReturnDate(bookLending.getReturnDate());
        return bookLendingEntity;
    }

    @Override
    public BookLending map(BookLendingEntity bookLendingEntity) {
        return new BookLending(bookLendingEntity.getIssueId(),
                bookLendingEntity.getBookItemId(),
                bookLendingEntity.getIssuerId(),
                bookLendingEntity.getIssueDate(),
                BookLending.LendingStatus.valueOf(bookLendingEntity.getLendingStatus()),
                bookLendingEntity.getReturnDate());
    }

    @Override
    public BookReservationEntity map(BookReservation bookReservation) {
        BookReservationEntity bookReservationEntity = new BookReservationEntity();
        bookReservationEntity.setBookIsbn(bookReservation.getBookIsbn());
        bookReservationEntity.setIssueDate(bookReservation.getIssueDate());
        bookReservationEntity.setReservationStatus(bookReservation.getReservationStatus().name());
        bookReservationEntity.setIssueId(bookReservation.getIssueId());
        bookReservationEntity.setIssuerId(bookReservation.getIssuerId());
        return bookReservationEntity;
    }

    @Override
    public BookReservation map(BookReservationEntity bookReservationEntity) {
        return new BookReservation(bookReservationEntity.getIssueId(),
                bookReservationEntity.getBookIsbn(),
                bookReservationEntity.getIssuerId(),
                bookReservationEntity.getIssueDate(),
                BookReservation.ReservationStatus.valueOf(bookReservationEntity.getReservationStatus()));
    }
}

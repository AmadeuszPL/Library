package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.BookLending;
import com.amadeusz.library.application.BookReservation;

public class DefaultBookIssueEntityMapper implements BookIssueMapper {

    @Override
    public BookLendingEntity map(BookLending bookLending) {
        BookLendingEntity bookLendingEntity = new BookLendingEntity();
        bookLendingEntity.setBookItemId(bookLending.getBookItemId());
        bookLendingEntity.setIssueDate(bookLending.getIssueDate());
        bookLendingEntity.setIssueId(bookLending.getIssueId());
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
        bookReservationEntity.setBookItemId(bookReservation.getBookItemId());
        bookReservationEntity.setIssueDate(bookReservation.getIssueDate());
        bookReservationEntity.setReservationStatus(bookReservation.getReservationStatus().name());
        bookReservationEntity.setIssueId(bookReservation.getIssueId());
        bookReservationEntity.setIssuerId(bookReservation.getIssuerId());
        return bookReservationEntity;
    }

    @Override
    public BookReservation map(BookReservationEntity bookReservationEntity) {
        return new BookReservation(bookReservationEntity.getIssueId(),
                bookReservationEntity.getBookItemId(),
                bookReservationEntity.getIssuerId(),
                bookReservationEntity.getIssueDate(),
                BookReservation.ReservationStatus.valueOf(bookReservationEntity.getReservationStatus()));
    }
}

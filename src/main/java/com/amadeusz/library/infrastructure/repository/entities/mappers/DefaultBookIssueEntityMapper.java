package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.bookissue.BookLending;
import com.amadeusz.library.application.model.bookissue.BookReservation;
import com.amadeusz.library.infrastructure.repository.entities.BookLendingEntity;
import com.amadeusz.library.infrastructure.repository.entities.BookReservationEntity;

public class DefaultBookIssueEntityMapper implements BookIssueMapper {

    @Override
    public BookLendingEntity map(BookLending bookLending) {
        BookLendingEntity bookLendingEntity = new BookLendingEntity();
        bookLendingEntity.setBookItemId(bookLending.getBookItemId());
        bookLendingEntity.setIssueDate(bookLending.getIssueDate());
        bookLendingEntity.setLendingId(bookLending.getIssueId());
        bookLendingEntity.setIssuerId(bookLending.getIssuerId());
        bookLendingEntity.setLendingStatus(bookLending.getLendingStatus().name());
        bookLendingEntity.setReturnDate(bookLending.getReturnDate());
        return bookLendingEntity;
    }

    @Override
    public BookLending map(BookLendingEntity bookLendingEntity) {
        return new BookLending(bookLendingEntity.getLendingId(), bookLendingEntity.getBookItemId(),
                bookLendingEntity.getIssuerId(), bookLendingEntity.getIssueDate(),
                BookLending.LendingStatus.valueOf(bookLendingEntity.getLendingStatus()),
                bookLendingEntity.getReturnDate());
    }

    @Override
    public BookReservationEntity map(BookReservation bookReservation) {
        BookReservationEntity bookReservationEntity = new BookReservationEntity();
        bookReservationEntity.setBookIsbn(bookReservation.getBookIsbn());
        bookReservationEntity.setIssueDate(bookReservation.getIssueDate());
        bookReservationEntity.setReservationStatus(bookReservation.getReservationStatus().name());
        bookReservationEntity.setReservationId(bookReservation.getIssueId());
        bookReservationEntity.setIssuerId(bookReservation.getIssuerId());
        return bookReservationEntity;
    }

    @Override
    public BookReservation map(BookReservationEntity bookReservationEntity) {
        return new BookReservation(bookReservationEntity.getReservationId(), bookReservationEntity.getBookIsbn(),
                bookReservationEntity.getIssuerId(), bookReservationEntity.getIssueDate(),
                BookReservation.ReservationStatus.valueOf(bookReservationEntity.getReservationStatus()));
    }

}

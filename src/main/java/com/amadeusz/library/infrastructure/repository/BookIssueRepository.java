package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.application.bookissue.BookLending;
import com.amadeusz.library.application.bookissue.BookReservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookIssueRepository {

    void createLending(BookLending bookLending);

    void createReservation(BookReservation bookReservation);

    BookReservation readReservation(UUID reservationUUID);

    List<BookReservation> readSortedListOfPendingBookReservations(String bookIsbn);

    UUID readLenderIdByBookItemId(UUID bookItemId);

    BookLending getBookLendingByBookItemId(UUID bookItemId);

    BookLending getLendingByLendingId(UUID bookLendingId);

    BookReservation getReservationByReservationId(UUID bookReservationId);

    void updateReservation(BookReservation bookReservation);

    void updateLending(BookLending bookLending);

    void deleteReservation(BookReservation bookReservation);

    void deleteLending(BookLending bookLending);


}

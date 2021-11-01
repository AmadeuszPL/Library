package com.amadeusz.library.application;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookIssueRepository {

    void saveLending(BookLending bookLending);

    void saveReservation(BookReservation bookReservation);

    BookReservation getReservation(UUID reservationUUID);

    List<BookReservation> getSortedListOfActiveBookReservations(UUID bookId);

    Optional<BookLending> getLendingByBookId(UUID bookId);
}

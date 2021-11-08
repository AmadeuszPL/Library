package com.amadeusz.library.infrastructure.bookissue;

import com.amadeusz.library.application.bookissue.BookLending;
import com.amadeusz.library.application.bookissue.BookReservation;

public interface BookIssueMapper {

    BookLendingEntity map(BookLending bookLending);

    BookLending map(BookLendingEntity bookLendingEntity);

    BookReservationEntity map(BookReservation bookReservation);

    BookReservation map(BookReservationEntity bookReservationEntity);

}

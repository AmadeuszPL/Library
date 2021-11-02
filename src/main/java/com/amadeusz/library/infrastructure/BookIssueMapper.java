package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.BookLending;
import com.amadeusz.library.application.BookReservation;

public interface BookIssueMapper {

    BookLendingEntity map(BookLending bookLending);

    BookLending map(BookLendingEntity bookLendingEntity);

    BookReservationEntity map(BookReservation bookReservation);

    BookReservation map(BookReservationEntity bookReservationEntity);

}

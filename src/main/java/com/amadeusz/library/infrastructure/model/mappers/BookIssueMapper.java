package com.amadeusz.library.infrastructure.model.mappers;

import com.amadeusz.library.application.bookissue.BookLending;
import com.amadeusz.library.application.bookissue.BookReservation;
import com.amadeusz.library.infrastructure.model.BookReservationEntity;
import com.amadeusz.library.infrastructure.model.BookLendingEntity;

public interface BookIssueMapper {

    BookLendingEntity map(BookLending bookLending);

    BookLending map(BookLendingEntity bookLendingEntity);

    BookReservationEntity map(BookReservation bookReservation);

    BookReservation map(BookReservationEntity bookReservationEntity);

}

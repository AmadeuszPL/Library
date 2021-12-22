package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.bookissue.BookLending;
import com.amadeusz.library.application.model.bookissue.BookReservation;
import com.amadeusz.library.infrastructure.repository.entities.BookReservationEntity;
import com.amadeusz.library.infrastructure.repository.entities.BookLendingEntity;

public interface BookIssueMapper {

    BookLendingEntity map(BookLending bookLending);

    BookLending map(BookLendingEntity bookLendingEntity);

    BookReservationEntity map(BookReservation bookReservation);

    BookReservation map(BookReservationEntity bookReservationEntity);

}

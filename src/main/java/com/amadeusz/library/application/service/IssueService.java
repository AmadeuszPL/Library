package com.amadeusz.library.application.service;

import com.amadeusz.library.application.model.bookissue.BookLending;
import com.amadeusz.library.application.model.bookissue.BookReservation;

import java.util.UUID;

public interface IssueService {

    BookLending lendBook(String isbn, UUID issuerId);

    BookReservation reserveBook(String isbn, UUID issuerId);

    BookLending returnBook(UUID bookItemId);

    BookReservation cancelReservation(String isbn, UUID issuerId);

}

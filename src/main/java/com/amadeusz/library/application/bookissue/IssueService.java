package com.amadeusz.library.application.bookissue;

import com.amadeusz.library.application.bookitem.BookItem;

import java.util.UUID;

public interface IssueService {

    void lendBook(String isbn, UUID issuerId) throws Exception;

    void updateBookStatus(BookItem.BookItemStatus bookStatus, UUID bookId) throws Exception;

    void reserveBook(String isbn, UUID issuerId);

    void returnBook(UUID bookItemId);

    void cancelReservation(String isbn, UUID issuerId);

}

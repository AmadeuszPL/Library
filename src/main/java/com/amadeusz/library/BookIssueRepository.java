package com.amadeusz.library;

import java.util.List;
import java.util.UUID;

public interface BookIssueRepository {

    void addIssue(BookIssue bookIssue, LibraryMember user) throws IllegalAccessException;

    void returnBook(UUID bookId, LibraryMember user) throws IllegalAccessException;

    UUID whoTookBookID(UUID book);

    List<UUID> booksRentedByUser(LibraryMember user);

    void reserveBook(UUID book, LibraryMember user) throws IllegalAccessException;

    void setAllRecordsRentalDataForTest();
}

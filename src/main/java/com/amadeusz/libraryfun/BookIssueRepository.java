package com.amadeusz.libraryfun;

import java.util.List;
import java.util.UUID;

public interface BookIssueRepository {

    void addIssue(BookIssue bookIssue, LibraryMember user);

    void returnBook(UUID bookId, LibraryMember user) throws IllegalAccessException;

    UUID whoTookBookID(UUID book);

    List<UUID> booksRentedByUser(LibraryMember user);
}

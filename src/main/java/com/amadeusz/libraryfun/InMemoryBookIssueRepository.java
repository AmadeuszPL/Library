package com.amadeusz.libraryfun;

import org.apache.commons.lang3.ObjectUtils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

class InMemoryBookIssueRepository implements BookIssueRepository {

    private final Map<UUID, BookIssue> issueRepository;

    public InMemoryBookIssueRepository() {
        this.issueRepository = new HashMap<>();
    }

    @Override
    public void addIssue(BookIssue bookIssue, LibraryMember user) {
        if (user.getTotalBooksCheckedOut() == ConstantValues.MAX_BOOKS_ISSUED_BY_USER
                && bookIssue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
            throw new IllegalArgumentException("Books limit reached");
        }
        bookIssue.setIssuer(user.getId());
        UUID id = bookIssue.getBookId();
        if (checkIfIssueInRepository(id)) {
            issueRepository.put(id, bookIssue);
            if (bookIssue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
                user.addOneBook();
            }
        } else if (checkIfBookAvailable(bookIssue)) {
            issueRepository.put(id, bookIssue);
            if (bookIssue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
                user.addOneBook();
            }
        } else if (checkIfReservedByIssuer(id, bookIssue)) {
            issueRepository.put(id, bookIssue);
            if (bookIssue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
                user.addOneBook();
            }
        } else if (checkIfLoanedByIssuer(id, bookIssue)) {
            System.out.println("You have already rented this book");
        } else if (checkIfReservationExpired(id)) {
            issueRepository.put(id, bookIssue);
            if (bookIssue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
                user.addOneBook();
            }
        } else {
            System.out.println("Book is not available");
        }
    }

    private boolean checkIfBookAvailable(BookIssue bookIssue) {
        return issueRepository.get(bookIssue.getBookId()).getBookStatus()
                .equals(BookIssue.BookStatus.AVAILABLE);
    }

    @Override
    public void returnBook(UUID bookId, LibraryMember user) throws IllegalAccessException {
        if (issueRepository.get(bookId) == null) {
            throw new NullPointerException("Book not in issue repository");
        }
        BookIssue issue = issueRepository.get(bookId);

        if (issue.getIssuer().equals(user.getId())) {
            if (!issue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
                throw new IllegalAccessException("You can return only loaned " +
                        "books!");
            }
            long rentalTime = DAYS.between(issue.getIssueDate(), LocalDate.now())
            if (rentalTime > 10) {
                new Fine(rentalTime-10)
            }
            issue.setIssuer(null);
            issue.setBookStatus(BookIssue.BookStatus.AVAILABLE);
        } else {
            throw new IllegalAccessException("You cannot return book that " +
                    "was loaned by another user");
        }
    }


    @Override
    public UUID whoTookBookID(UUID book) {
        for (BookIssue bookIssue : issueRepository.values()) {
            if (bookIssue.getBookId().equals(book)) {
                return bookIssue.getIssuer();
            }
        }
        return null;
    }

    @Override
    public List<UUID> booksRentedByUser(LibraryMember user) {
        List<UUID> list = new ArrayList<>();
        for (BookIssue issue : issueRepository.values()) {
            if (issue.getIssuer().equals(user.getId()) && issue.getBookStatus()
                    .equals(BookIssue.BookStatus.LOANED)) {
                list.add(issue.getBookId());
            }
        }
        return list;
    }

    private boolean checkIfReservationExpired(UUID id) {
        return issueRepository.get(id).getBookStatus().equals(BookIssue.BookStatus.RESERVED)
                && (DAYS.between(issueRepository.get(id).getIssueDate(),
                LocalDate.now()) > 5);
    }

    private boolean checkIfLoanedByIssuer(UUID id, BookIssue bookIssue) {
        return issueRepository.get(id).getBookStatus().equals(BookIssue.BookStatus.LOANED)
                && issueRepository.get(id).getIssuer().equals(bookIssue.getIssuer());
    }

    private boolean checkIfReservedByIssuer(UUID id, BookIssue bookIssue) {
        return issueRepository.get(id).getBookStatus()
                .equals(BookIssue.BookStatus.RESERVED) &&
                issueRepository.get(id).getIssuer().equals(bookIssue.getIssuer());
    }

    private boolean checkIfIssueInRepository(UUID id) {
        return issueRepository.get(id) == null;
    }

    @Override
    public String toString() {
        return issueRepository.values() + "";
    }
}

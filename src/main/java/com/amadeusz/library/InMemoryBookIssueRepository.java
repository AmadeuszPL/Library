package com.amadeusz.library;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

class InMemoryBookIssueRepository implements BookIssueRepository {

    private final Map<UUID, BookIssue> issueRepository;

    public InMemoryBookIssueRepository() {
        this.issueRepository = new HashMap<>();
    }

    @Override
    public void addIssue(BookIssue bookIssue, LibraryMember user) throws IllegalAccessException {
        if (user.getTotalBooksCheckedOut() == ConstantValues.MAX_BOOKS_ISSUED_BY_USER
                && bookIssue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
            throw new IllegalArgumentException("Books limit reached");
        }
        if (user.getFine() != null && user.getFine() != BigDecimal.valueOf(0)) {
            throw new IllegalAccessException("You need to pay fine before " +
                    "renting more books.");
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
    public void returnBook(UUID bookId, LibraryMember user)
            throws IllegalAccessException {
        checkIfBookInRepository(bookId);
        BookIssue issue = issueRepository.get(bookId);

        if (issue.getIssuer().equals(user.getId())) {
            if (!issue.getBookStatus().equals(BookIssue.BookStatus.LOANED)) {
                throw new IllegalAccessException("You can return only loaned " +
                        "books!");
            }
            long rentalTime = DAYS.between(issue.getIssueDate(),
                    LocalDate.now());
            if (rentalTime > ConstantValues.MAX_LENDING_DAYS) {
                long fine = (rentalTime - ConstantValues.MAX_LENDING_DAYS)
                        * ConstantValues.FINE_FOR_ONE_DAY_OF_RENTAL;
                System.out.println("Book was returned after due date, you " +
                        "need to pay fine!");
                user.addFine(BigDecimal.valueOf(fine));
            }
            if (issue.getReservationStatus() == BookIssue.ReservationOfLoanedBookStatus.RESERVED) {
                issue.setIssuer(issue.getBooker());
                issue.setIssueDate(LocalDate.now());
                issue.setBookStatus(BookIssue.BookStatus.RESERVED);
                issue.setReservationStatus(null);
                issue.setBooker(null);
                System.out.println("Your book is avaliable - your reservation" +
                        " is valid for 5 days from now.");
            }
            issue.setIssuer(null);
            issue.setBookStatus(BookIssue.BookStatus.AVAILABLE);
            user.subtractOneBook();
        } else {
            throw new IllegalAccessException("You cannot return book that " +
                    "was loaned by another user");
        }
    }

    private void checkIfBookInRepository(UUID bookId) {
        if (issueRepository.get(bookId) == null) {
            throw new NullPointerException("Book not in issue repository");
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

    @Override
    public void reserveBook(UUID bookId, LibraryMember user) throws IllegalAccessException {
        checkIfBookInRepository(bookId);
        BookIssue.BookStatus bookStatus = getBookStatus(bookId);
        BookIssue.ReservationOfLoanedBookStatus reservationStatus =
                getReservationStatus(bookId);
        BookIssue bookIssue = issueRepository.get(bookId);

        if (bookStatus.equals(BookIssue.BookStatus.LOANED)
                && (reservationStatus == null ||
        !(reservationStatus.equals(BookIssue.ReservationOfLoanedBookStatus.RESERVED)))) {
            if (bookIssue.getBooker() != null && bookIssue.getBooker().equals(user.getId())) {
                throw new IllegalAccessException("Book loaned by user cannot " +
                        "be reserved by user");
            }
            bookIssue.setBooker(user.getId());
            bookIssue.setReservationStatus(BookIssue.ReservationOfLoanedBookStatus.RESERVED);
        } else if (reservationStatus.equals(BookIssue.ReservationOfLoanedBookStatus.RESERVED)) {
            throw new IllegalAccessException("Book already reserved");
        } else if (checkIfReservationExpired(bookId)) {
            bookIssue.setIssueDate(LocalDate.now());
            bookIssue.setIssuer(user.getId());
        } else if (bookStatus.equals(BookIssue.BookStatus.AVAILABLE)) {
            bookIssue.setIssueDate(LocalDate.now());
            bookIssue.setIssuer(user.getId());
            bookIssue.setBookStatus(BookIssue.BookStatus.RESERVED);
        } else {
            throw new IllegalAccessException("Book reserved wait until " +
                    "reservation expires");
        }
    }

    private BookIssue.BookStatus getBookStatus(UUID bookId) {
        return issueRepository.get(bookId).getBookStatus();
    }

    private BookIssue.ReservationOfLoanedBookStatus getReservationStatus(UUID bookId) {
        return issueRepository.get(bookId).getReservationStatus();
    }

    @Override
    public void setAllRecordsRentalDataForTest() {
        for (BookIssue bookIssue : issueRepository.values()) {
            bookIssue.setIssueDate(bookIssue.getIssueDate().minusDays(20));
        }
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

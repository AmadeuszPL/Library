package com.amadeusz.library.application;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

public class IssueService {

    private final BookIssueRepository issueRepository;

    public IssueService(BookIssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }
/*
    public void lendBook(UUID bookId, LibraryMember issuer) {
        checkIfUserReachedLimit(issuer.getTotalBooksCheckedOut());
        checkIfBookIsNotLendedAlready(bookId);
        checkIfBookIsNotAlreadyReservedAndUpdateStatuses(bookId, issuer.getId());
        issueRepository.saveLending(new BookLending(bookId, issuer));
        issuer.addOneBook();
    }

    public void reserveBook(UUID bookId, LibraryMember issuer) {
        if (!checkIfBookIsNotReservedByIssuer(bookId, issuer.getId())) {
            throw new IllegalOperationException("You already reserved this " +
                    "book.");
        }
        checkIfBookLendedByIssuer(bookId, issuer.getId());
        if (checkIfBookHasReservationOnIt(bookId)) {
            issueRepository.saveReservation(new BookReservation(bookId, issuer
                    , BookReservation.ReservationStatus.ACTIVE));
        } else {
            issueRepository.saveReservation(new BookReservation(bookId, issuer
                    , BookReservation.ReservationStatus.PENDING));
        }
    }

    public void returnBook(UUID bookId) {
        BookLending lending = checkIfBookWasLended(bookId);
        addFineIfReturnedAfterTime(lending);
        notifyActiveReservator(bookId);
        updateLendingStatusToReturned(lending);
    }

    public void cancelReservation(UUID bookId, LibraryMember issuer) {
        List<BookReservation> bookReservations =
                issueRepository.getSortedListOfActiveBookReservations(bookId);

        UUID userId = issuer.getId();
        if (!bookReservations.isEmpty()) {
            if (checkIfFirstReservationByIssuerAndUpdateStatus(bookReservations,
                    userId, bookId)) {
                if (checkIfBookIsNotReservedByIssuer(bookId, userId)) {
                    bookReservations.stream()
                            .filter(bookReservation ->
                                    bookReservation.getReservationStatus().equals(BookReservation.ReservationStatus.PENDING))
                            .forEach(bookReservation -> bookReservation.setReservationStatus(BookReservation.ReservationStatus.EXPIRED));
                }
            }
        }
    }


    private void notifyIssuerThatBookReturnedAfterTime(LibraryMember issuer,
                                                       UUID bookId) {
        issuer.sendNotificationBookReturnedAfterTime(bookId);
    }

    private void updateLendingStatusToReturned(BookLending lending) {
        lending.setLendingStatus(BookLending.LendingStatus.RETURNED);
        lending.setReturnDate(LocalDateTime.now());
    }

    private void notifyActiveReservator(UUID bookId) {
        List<BookReservation> list =
                issueRepository.getSortedListOfActiveBookReservations(bookId);
        if (!list.isEmpty()) {
            list.get(0).getIssuer().sendNotificationBookAvailable(bookId);
        }
    }

    private BookLending checkIfBookWasLended(UUID bookId) {
        Optional<BookLending> foundLending = issueRepository.getLendingByBookId(bookId);
        if (foundLending.isEmpty()) {
            throw new IllegalOperationException("Book not lended yet");
        }
        return foundLending.get();
    }

    private void addFineIfReturnedAfterTime(BookLending foundLending) {
        long rentingTime = DAYS.between(foundLending.getIssueDate(),
                LocalDateTime.now());

        if (rentingTime > ConstantValues.MAX_LENDING_DAYS) {
            foundLending.getIssuer()
                    .addFine(BigDecimal.valueOf((
                            rentingTime - ConstantValues.MAX_LENDING_DAYS)
                            * ConstantValues.FINE_FOR_ONE_DAY_OF_RENTAL));
            notifyIssuerThatBookReturnedAfterTime(foundLending.getIssuer(),
                    foundLending.getBookId());
            foundLending.getIssuer().subtractOneBook();
        }
    }

    private boolean checkIfBookHasReservationOnIt(UUID bookId) {
        List<BookReservation> list =
                issueRepository.getSortedListOfActiveBookReservations(bookId);
        return list.isEmpty();
    }

    private void checkIfBookLendedByIssuer(UUID bookId, UUID userId) {
        Optional<BookLending> lending =
                issueRepository.getLendingByBookId(bookId);
        if (lending.isPresent() && lending.get().getIssuer().getId().equals(userId)) {
            throw new AccessInMemoryIssueRepositoryException("You cannot " +
                    "reserve book that you have already lended");
        }
    }

    private boolean checkIfBookIsNotReservedByIssuer(UUID bookId, UUID userId) {
        List<BookReservation> list =
                issueRepository.getSortedListOfActiveBookReservations(bookId);

        Optional<BookReservation> bookIsReservedByUser = list.stream()
                .filter(bookReservation -> bookReservation.getIssueId().equals(userId))
                .filter(bookReservation -> !(bookReservation.getReservationStatus()
                        .equals(BookReservation.ReservationStatus.EXPIRED)))
                .findAny();
        return bookIsReservedByUser.equals(Optional.empty());
    }

    private void checkIfUserReachedLimit(int totalBooksCheckedOut) {
        if (totalBooksCheckedOut >= 5) {
            throw new IllegalOperationException("You've already reached book " +
                    "limit");
        }
    }

    private void checkIfBookIsNotAlreadyReservedAndUpdateStatuses(UUID bookId, UUID userId) {
        List<BookReservation> list =
                issueRepository.getSortedListOfActiveBookReservations(bookId);
        if (!list.isEmpty()) {
            if (checkIfFirstReservationByIssuerAndUpdateStatus(list, userId,
                    bookId)) {
                throw new AccessInMemoryIssueRepositoryException("Book is " +
                        "reserved");
            }
        }
    }

    private boolean checkIfFirstReservationByIssuerAndUpdateStatus(List<BookReservation> list, UUID userId, UUID bookId) {

        if (compareUserWithBookIssuer(list.get(0).getIssuerId(), userId)) {
            list.get(0).setReservationStatus(BookReservation.ReservationStatus.EXPIRED);
            if (moreThanOneReservation(list.size())) {
                list.get(1).setReservationStatus(BookReservation.ReservationStatus.ACTIVE);
                sendNotificationThatReservationIsActive(list.get(1).getIssueId(), bookId);
            }
            return false;
        }
        return true;
    }


    private void sendNotificationThatReservationIsActive(UUID issueId,
                                                         UUID bookId) {
        BookIssue issue = issueRepository.getReservation(issueId);
        issue.getIssuer().sendNotificationBookAvailable(bookId);
    }

    private boolean moreThanOneReservation(int size) {
        return size > 1;
    }


    private boolean compareUserWithBookIssuer(UUID issuerId, UUID userId) {
        return issuerId == userId;
    }


    private void checkIfBookIsNotLendedAlready(UUID bookId) {
        if (!issueRepository.getLendingByBookId(bookId).equals(Optional.empty())) {
            throw new AccessInMemoryIssueRepositoryException("Book already " +
                    "lended");
        }
    }

 */

    @Override
    public String toString() {
        return "IssueService{" + issueRepository +
                '}';
    }
}
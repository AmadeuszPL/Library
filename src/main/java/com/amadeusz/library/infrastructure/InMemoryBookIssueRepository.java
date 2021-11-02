package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.BookIssueRepository;
import com.amadeusz.library.application.BookLending;
import com.amadeusz.library.application.BookReservation;

import java.util.*;
import java.util.stream.Collectors;

import static com.amadeusz.library.application.BookLending.LendingStatus.ACTIVE;

public class InMemoryBookIssueRepository implements BookIssueRepository {

    private final Map<UUID, BookLending> lendingRepository;
    private final Map<UUID, BookReservation> reservationRepository;

    public InMemoryBookIssueRepository() {
        this.lendingRepository = new HashMap<>();
        this.reservationRepository = new HashMap<>();
    }


    @Override
    public void saveLending(BookLending bookLending) {
        lendingRepository.put(bookLending.getIssueId(), bookLending);
    }

    @Override
    public void saveReservation(BookReservation bookReservation) {
        reservationRepository.put(bookReservation.getIssueId(),
                bookReservation);

    }

    @Override
    public BookReservation getReservation(UUID reservationUUID) {
        return reservationRepository.get(reservationUUID);
    }

    @Override
    public List<BookReservation> getSortedListOfActiveBookReservations(UUID bookId) {
        return null;
    }

    @Override
    public Optional<BookLending> getLendingByBookId(UUID bookId) {
        return Optional.empty();
    }

    /*    @Override
        public List<BookReservation> getSortedListOfActiveBookReservations(UUID bookId) {
            return reservationRepository.values().stream()
                    .filter(bookReservation -> bookReservation.getBookId().equals(bookId))
                    .filter(bookReservation -> !(bookReservation
                            .getReservationStatus().
                            equals(BookReservation.ReservationStatus.EXPIRED)))
                    .sorted(Comparator.comparing(BookReservation::getIssueDate))
                    .collect(Collectors.toList());
        }

        @Override
        public Optional<BookLending> getLendingByBookId(UUID bookId) {
            return lendingRepository.values().stream()
                    .filter(bookLending -> bookLending.getBookId().equals(bookId))
                    .filter(bookLending -> bookLending.getStatus().equals(ACTIVE))
                    .findFirst();
        }
    */
    @Override
    public String toString() {
        return "lendingRepository=" + lendingRepository +
                "\n reservationRepository=" + reservationRepository +
                '}';
    }
}

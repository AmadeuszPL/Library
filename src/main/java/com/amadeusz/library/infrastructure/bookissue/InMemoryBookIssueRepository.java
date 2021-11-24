package com.amadeusz.library.infrastructure.bookissue;

import com.amadeusz.library.application.bookissue.BookIssue;
import com.amadeusz.library.application.bookissue.BookIssueRepository;
import com.amadeusz.library.application.bookissue.BookLending;
import com.amadeusz.library.application.bookissue.BookReservation;
import com.amadeusz.library.infrastructure.bookitem.NoBookInRepositoryException;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("BookIssueRepository")
public class InMemoryBookIssueRepository implements BookIssueRepository {

    private final BookIssueMapper mapper;
    private final Map<UUID, BookLendingEntity> lendingRepository;
    private final Map<UUID, BookReservationEntity> reservationRepository;

    public InMemoryBookIssueRepository() {
        this.mapper = new DefaultBookIssueEntityMapper();
        this.lendingRepository = new HashMap<>();
        this.reservationRepository = new HashMap<>();
    }


    @Override
    public void createLending(BookLending bookLending) {
        lendingRepository.put(bookLending.getIssueId(),
                mapper.map(bookLending));
    }

    @Override
    public void createReservation(BookReservation bookReservation) {
        reservationRepository.put(bookReservation.getIssueId(),
                mapper.map(bookReservation));

    }

    @Override
    public BookReservation readReservation(UUID reservationUUID) {
        return mapper.map(reservationRepository.get(reservationUUID));
    }

    @Override
    public BookLending getLendingByLendingId(UUID bookLendingId) {
        return mapper.map(lendingRepository.get(bookLendingId));
    }

    @Override
    public BookReservation getReservationByReservationId(UUID bookReservationId) {
        return mapper.map(reservationRepository.get(bookReservationId));
    }


    public void updateReservation(BookReservation bookReservation) {
        reservationRepository.computeIfPresent(bookReservation.getIssueId(),
                (k, v) -> mapper.map(bookReservation));
    }

    @Override
    public void updateLending(BookLending bookLending) {
        lendingRepository.computeIfPresent(bookLending.getIssueId(),
                (k, v) -> mapper.map(bookLending));
    }

    @Override
    public void deleteReservation(BookReservation bookReservation) {
        reservationRepository.remove(bookReservation.getIssueId());
    }

    @Override
    public void deleteLending(BookLending bookLending) {
        lendingRepository.remove(bookLending.getIssueId());
    }

    @Override
    public List<BookReservation> readSortedListOfPendingBookReservations(String bookIsbn) {
        return reservationRepository.values().stream()
                .filter(bookReservationEntity -> bookReservationEntity
                        .getBookIsbn().equals(bookIsbn))
                .filter(bookReservationEntity ->
                        bookReservationEntity.getReservationStatus()
                                .equals(BookReservation.ReservationStatus.PENDING.name()))
                .map(mapper::map)
                .sorted(Comparator.comparing(BookIssue::getIssueDate))
                .collect(Collectors.toList());
    }

    @Override
    public UUID readLenderIdByBookItemId(UUID bookItemId) {
        return lendingRepository.values().stream()
                .filter(bookLendingEntity -> bookLendingEntity
                        .getBookItemId().equals(bookItemId))
                .filter(bookLendingEntity -> bookLendingEntity
                        .getLendingStatus().equals("ACTIVE"))
                .map(BookLendingEntity::getIssuerId)
                .findAny().get();
    }

    @Override
    public BookLending getBookLendingByBookItemId(UUID bookItemId) {
        Optional<BookLending> lended = lendingRepository.values()
                .stream()
                .filter(bookLendingEntity -> bookLendingEntity.getBookItemId().equals(bookItemId))
                .filter(bookLendingEntity -> bookLendingEntity
                        .getLendingStatus().equals("ACTIVE"))
                .map(mapper::map)
                .findFirst();

        if (lended.isPresent()) {
            return lended.get();
        } else {
            throw new NoBookInRepositoryException("Book lending not in repository");
        }
    }

    @Override
    public String toString() {
        return "lendingRepository=" + lendingRepository +
                "\n reservationRepository=" + reservationRepository +
                '}';
    }
}

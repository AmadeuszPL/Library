package com.amadeusz.library.application.model.bookissue;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookReservation extends BookIssue {

    private ReservationStatus reservationStatus;
    private final String bookIsbn;

    public BookReservation(UUID issueId, String bookIsbn, UUID issuerId,
                           LocalDateTime localDateTime,
                           ReservationStatus reservationStatus) {
        super(issueId, localDateTime, issuerId);
        this.bookIsbn = bookIsbn;
        this.reservationStatus = reservationStatus;
    }

    public BookReservation(UUID issuerId, String bookIsbn) {

        super(UUID.randomUUID(), LocalDateTime.now(), issuerId);
        this.bookIsbn = bookIsbn;
        this.reservationStatus = ReservationStatus.PENDING;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public void setExpiredStatus() {
        reservationStatus = ReservationStatus.EXPIRED;
    }

    public enum ReservationStatus {

        PENDING,
        EXPIRED

    }
}

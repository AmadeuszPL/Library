package com.amadeusz.library.application;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookReservation extends BookIssue {

    private ReservationStatus reservationStatus;

    public BookReservation(UUID issueId, UUID bookItemId, UUID issuerId, LocalDateTime localDateTime, ReservationStatus reservationStatus) {
        super(issueId, bookItemId, issuerId, localDateTime);
        this.reservationStatus = reservationStatus;
    }

    public BookReservation(UUID bookId, UUID issuerId,
                           ReservationStatus reservationStatus) {

        super(UUID.randomUUID(), bookId, issuerId, LocalDateTime.now());
        this.reservationStatus = reservationStatus;

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

    public void setActiveStatus() {
        reservationStatus = ReservationStatus.ACTIVE;
    }

    public enum ReservationStatus {

        ACTIVE,
        PENDING,
        EXPIRED

    }
}

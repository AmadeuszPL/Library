package com.amadeusz.library.application;

import java.util.UUID;

public class BookReservation extends BookIssue {

    private ReservationStatus reservationStatus;

    public BookReservation(UUID bookId, LibraryMember issuer, ReservationStatus reservationStatus) {

        super(bookId, issuer);
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

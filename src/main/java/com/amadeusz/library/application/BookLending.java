package com.amadeusz.library.application;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookLending extends BookIssue {

    private LendingStatus lendingStatus;
    private LocalDateTime returnDate;

    public BookLending(UUID issueId, UUID bookItemId, UUID issuerId, LocalDateTime localDateTime, LendingStatus lendingStatus, LocalDateTime returnDate) {
        super(issueId, bookItemId, issuerId, localDateTime);
        this.lendingStatus = lendingStatus;
        this.returnDate = returnDate;
    }

    public BookLending(UUID bookId, UUID issuerId) {
        super(UUID.randomUUID(), bookId, issuerId, LocalDateTime.now());
        this.lendingStatus = LendingStatus.ACTIVE;
    }

    public void setLendingStatus(LendingStatus lendingStatus) {
        this.lendingStatus = lendingStatus;
    }

    public LendingStatus getLendingStatus() {
        return lendingStatus;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public enum LendingStatus {
        ACTIVE,
        RETURNED
    }

    @Override
    public String toString() {
        return "BookLending{" + super.toString() +
                "lendingStatus=" + lendingStatus +
                ", returnDate=" + returnDate +
                '}';
    }
}

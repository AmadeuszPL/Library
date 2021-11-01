package com.amadeusz.library.application;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookLending extends BookIssue {
    private LendingStatus lendingStatus;
    private LocalDateTime returnDate;

    public BookLending(UUID bookId, LibraryMember issuer) {

        super(bookId, issuer);
        this.lendingStatus = LendingStatus.ACTIVE;

    }

    public LendingStatus getStatus() {
        return lendingStatus;
    }

    public void setLendingStatus(LendingStatus lendingStatus) {
        this.lendingStatus = lendingStatus;
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

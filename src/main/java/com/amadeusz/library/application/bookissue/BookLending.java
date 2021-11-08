package com.amadeusz.library.application.bookissue;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookLending extends BookIssue {

    private LendingStatus lendingStatus;
    private LocalDateTime returnDate;
    private final UUID bookItemId;

    public BookLending(UUID issueId, UUID bookItemId, UUID issuerId, LocalDateTime localDateTime, LendingStatus lendingStatus, LocalDateTime returnDate) {
        super(issueId, issuerId, localDateTime);
        this.bookItemId = bookItemId;
        this.lendingStatus = lendingStatus;
        this.returnDate = returnDate;
    }

    public BookLending(UUID bookItemId, UUID issuerId) {
        super(UUID.randomUUID(), issuerId, LocalDateTime.now());
        this.bookItemId = bookItemId;
        this.lendingStatus = LendingStatus.ACTIVE;
    }

    public void setLendingStatus(LendingStatus lendingStatus) {
        this.lendingStatus = lendingStatus;
    }

    public UUID getBookItemId() {
        return bookItemId;
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
                "\n}";
    }
}

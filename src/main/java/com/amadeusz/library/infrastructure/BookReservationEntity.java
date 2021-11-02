package com.amadeusz.library.infrastructure;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookReservationEntity {

    private UUID issueId;
    private LocalDateTime issueDate;
    private UUID bookItemId;
    private UUID issuerId;
    private String reservationStatus;

    public UUID getIssueId() {
        return issueId;
    }

    public void setIssueId(UUID issueId) {
        this.issueId = issueId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDateTime issueDate) {
        this.issueDate = issueDate;
    }

    public UUID getBookItemId() {
        return bookItemId;
    }

    public void setBookItemId(UUID bookItemId) {
        this.bookItemId = bookItemId;
    }

    public UUID getIssuerId() {
        return issuerId;
    }

    public void setIssuerId(UUID issuerId) {
        this.issuerId = issuerId;
    }

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }
}

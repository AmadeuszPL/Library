package com.amadeusz.library.infrastructure.bookissue;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookReservationEntity {

    private UUID issueId;
    private LocalDateTime issueDate;
    private String bookIsbn;
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

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
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

    @Override
    public String toString() {
        return "BookReservationEntity{" +
                "issueId=" + issueId +
                ", issueDate=" + issueDate +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", issuerId=" + issuerId +
                ", reservationStatus='" + reservationStatus + '\'' +
                "}\n";
    }
}

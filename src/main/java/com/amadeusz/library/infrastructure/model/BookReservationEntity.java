package com.amadeusz.library.infrastructure.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="book_reservations")
public class BookReservationEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID reservationId;
    private LocalDateTime issueDate;
    private String bookIsbn;
    private UUID issuerId;
    private String reservationStatus;

    public BookReservationEntity() {
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID issueId) {
        this.reservationId = issueId;
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
                "issueId=" + reservationId +
                ", issueDate=" + issueDate +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", issuerId=" + issuerId +
                ", reservationStatus='" + reservationStatus + '\'' +
                "}\n";
    }
}

package com.amadeusz.library.infrastructure.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="book_lendings")
public class BookLendingEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID lendingId;
    private LocalDateTime issueDate;
    private UUID bookItemId;
    private UUID issuerId;
    private String lendingStatus;
    private LocalDateTime returnDate;

    public BookLendingEntity() {
    }

    public UUID getLendingId() {
        return lendingId;
    }

    public void setLendingId(UUID issueId) {
        this.lendingId = issueId;
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

    public String getLendingStatus() {
        return lendingStatus;
    }

    public void setLendingStatus(String lendingStatus) {
        this.lendingStatus = lendingStatus;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "BookLendingEntity{" +
                "issueId=" + lendingId +
                ", issueDate=" + issueDate +
                ", bookItemId=" + bookItemId +
                ", issuerId=" + issuerId +
                ", lendingStatus='" + lendingStatus + '\'' +
                ", returnDate=" + returnDate +
                "\n}";
    }
}

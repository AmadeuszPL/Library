package com.amadeusz.library.infrastructure.bookissue;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class BookLendingEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID issueId;
    private LocalDateTime issueDate;
    private UUID bookItemId;
    private UUID issuerId;
    private String lendingStatus;
    private LocalDateTime returnDate;

    public BookLendingEntity() {
    }

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
                "issueId=" + issueId +
                ", issueDate=" + issueDate +
                ", bookItemId=" + bookItemId +
                ", issuerId=" + issuerId +
                ", lendingStatus='" + lendingStatus + '\'' +
                ", returnDate=" + returnDate +
                "\n}";
    }
}

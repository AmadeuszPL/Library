package com.amadeusz.library.application;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BookIssue {

    private final UUID issueId;
    private final LocalDateTime issueDate;
    private final UUID bookItemId;
    private final UUID issuerId;

    public BookIssue(UUID issueId, UUID bookItemId, UUID issuerId,
                     LocalDateTime localDateTime) {

        this.issueId = issueId;
        this.issueDate = localDateTime;
        this.bookItemId = bookItemId;
        this.issuerId = issuerId;

    }

    public UUID getIssueId() {
        return issueId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public UUID getBookItemId() {
        return bookItemId;
    }

    public UUID getIssuerId() {
        return issuerId;
    }

    @Override
    public String toString() {
        return "BookIssue{" +
                "issueId=" + issueId +
                ", issueDate=" + issueDate +
                ", bookItemId=" + bookItemId +
                ", issuerId=" + issuerId +
                '}';
    }
}

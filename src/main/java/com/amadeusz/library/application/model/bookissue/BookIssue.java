package com.amadeusz.library.application.model.bookissue;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BookIssue {

    private final UUID issueId;
    private final LocalDateTime issueDate;
    private final UUID issuerId;

    public BookIssue(UUID issueId, UUID issuerId,
                     LocalDateTime localDateTime) {

        this.issueId = issueId;
        this.issueDate = localDateTime;
        this.issuerId = issuerId;

    }

    public UUID getIssueId() {
        return issueId;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public UUID getIssuerId() {
        return issuerId;
    }

    @Override
    public String toString() {
        return "BookIssue{" +
                "issueId=" + issueId +
                ", issueDate=" + issueDate +
                ", issuerId=" + issuerId +
                '}';
    }
}

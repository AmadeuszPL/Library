package com.amadeusz.library.application;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BookIssue {

    private final UUID issueId;
    private final LocalDateTime issueDate;
    private final UUID bookId;
    private final LibraryMember issuer;

    public BookIssue(UUID bookId, LibraryMember issuer) {

        this.issueId = UUID.randomUUID();
        this.issueDate = LocalDateTime.now();
        this.bookId = bookId;
        this.issuer = issuer;

    }

    public UUID getIssueId() {
        return issueId;
    }

    public UUID getBookId() {
        return bookId;
    }

    public UUID getIssuerId() {
        return issuer.getId();
    }

    public LibraryMember getIssuer() {
        return issuer;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    @Override
    public String toString() {
        return "BookIssue{" +
                "issueId=" + issueId +
                ", issueDate=" + issueDate +
                ", bookId=" + bookId +
                ", issuer=" + issuer +
                '}';
    }
}

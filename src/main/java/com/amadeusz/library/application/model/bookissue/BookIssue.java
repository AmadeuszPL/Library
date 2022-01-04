package com.amadeusz.library.application.model.bookissue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class BookIssue {

    private final UUID issueId;
    private final LocalDateTime issueDate;
    private final UUID issuerId;

}

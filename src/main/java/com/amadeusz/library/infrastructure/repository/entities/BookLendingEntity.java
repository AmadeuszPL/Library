package com.amadeusz.library.infrastructure.repository.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "book_lendings")
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

}

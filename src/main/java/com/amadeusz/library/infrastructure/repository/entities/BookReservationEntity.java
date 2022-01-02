package com.amadeusz.library.infrastructure.repository.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "book_reservations")
public class BookReservationEntity {

    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID reservationId;
    private LocalDateTime issueDate;
    private String bookIsbn;
    private UUID issuerId;
    private String reservationStatus;



}

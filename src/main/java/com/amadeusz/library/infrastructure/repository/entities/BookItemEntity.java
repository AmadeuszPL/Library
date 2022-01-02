package com.amadeusz.library.infrastructure.repository.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "book_items")
public class BookItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "bookItemId", updatable = false, nullable = false)
    private UUID id;
    private String bookIsbn;
    private String rackNumber;
    private String bookItemStatus;

}

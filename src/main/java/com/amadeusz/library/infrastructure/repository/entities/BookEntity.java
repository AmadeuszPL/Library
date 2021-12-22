package com.amadeusz.library.infrastructure.repository.entities;

import lombok.*;

import javax.persistence.*;


@Data
@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    private String isbn;
    private String title;
    private int publicationYear;
    private String authorName;
    private int authorBirthYear;
    private String category;

}

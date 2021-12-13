package com.amadeusz.library.application.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

public class Book {

    private final ISBN isbn;
    private String title;
    private final int publicationYear;
    private final Author author;
    private final SubjectCategory category;

    public Book(ISBN isbn, String title, int publicationYear, Author author,
                SubjectCategory category) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
//        Optional<SubjectCategory> any =
//                Arrays.stream(SubjectCategory.values()).filter(cat -> cat.equals(category)).findAny();
//        if (any.isEmpty()){
//            throw new IllegalArgumentException("Exception has occured");
//        }
        this.category = category;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public Author getAuthor() {
        return author;
    }

    public SubjectCategory getCategory() {
        return category;
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public enum SubjectCategory {

        ACTION,
        ADVENTURE,
        COMIC_BOOK,
        DETECTIVE,
        MYSTERY,
        FANTASY,
        HISTORICAL_FICTION,
        LITERALLY_FICTION,
        NOVEL,
        EPIC_POETRY

    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn.getValue() +
                ", title='" + title + '\'' +
                "\n";
    }
}

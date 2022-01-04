package com.amadeusz.library.application.model.book;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {

    private final ISBN isbn;
    private String title;
    private final int publicationYear;
    private final Author author;
    private final SubjectCategory category;

    public void updateTitle(String title) {
        this.title = title;
    }

    public enum SubjectCategory {
        ACTION, ADVENTURE, COMIC_BOOK, DETECTIVE, MYSTERY, FANTASY,
        HISTORICAL_FICTION, LITERALLY_FICTION, NOVEL, EPIC_POETRY
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn.getValue() + ", title='" + title + '\'' + "\n";
    }

}

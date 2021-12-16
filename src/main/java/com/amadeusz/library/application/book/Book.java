package com.amadeusz.library.application.book;

public class Book {

    private final ISBN isbn;
    private String title;
    private final int publicationYear;
    private final Author author;
    private final SubjectCategory category;

    public Book(ISBN isbn, String title, int publicationYear, Author author, SubjectCategory category) {
        this.isbn = isbn;
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
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
        ACTION, ADVENTURE, COMIC_BOOK, DETECTIVE, MYSTERY, FANTASY,
        HISTORICAL_FICTION, LITERALLY_FICTION, NOVEL, EPIC_POETRY
    }

    @Override
    public String toString() {
        return "Book{" + "isbn=" + isbn.getValue() + ", title='" + title + '\'' + "\n";
    }

}

package com.amadeusz.libraryfun;

import java.util.ArrayList;
import java.util.List;

public abstract class Book {

    private String ISBN;
    private String title;
    private int publicationYear;
    private Author author;
    private SubjectCategory category;


    public Book(String ISBN, String title, int publicationYear, Author author, SubjectCategory category) {
        this.ISBN = ISBN;
        this.title = title;
        this.publicationYear = publicationYear;
        this.author = author;
        this.category = category;
    }

    public String getISBN() {
        return ISBN;
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

/*    @Override
    public String toString() {
        return "Book{" +
                "\n ISBN='" + ISBN + '\'' +
                ",\n title='" + title + '\'' +
                ",\n publicationYear=" + publicationYear +
                ",\n author=" + author +
                ",\n category=" + category +
                "\n}";
    }*/

    public abstract void setIssueDate();

    public abstract Account getIssuer();
}

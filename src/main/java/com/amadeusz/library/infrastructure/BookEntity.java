package com.amadeusz.library.infrastructure;

public class BookEntity {

    private String isbn;
    private String title;
    private int publicationYear;
    private String authorName;
    private int authorBirthYear;
    private String category;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorBirthYear() {
        return authorBirthYear;
    }

    public void setAuthorBirthYear(int authorBirthYear) {
        this.authorBirthYear = authorBirthYear;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                "\n";
    }
}

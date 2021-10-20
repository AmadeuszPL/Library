package com.amadeusz.libraryfun;

public interface BookRepository {

    void searchByTitle(String title);
    void searchByCategory(SubjectCategory category);
    void searchByAuthor(String author);
    void searchByYear(int year);

}

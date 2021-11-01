package com.amadeusz.library.application;

import java.util.*;

import static com.amadeusz.library.infrastructure.InMemoryBookRepository.countBooks;

public class BookRepositoryService {

    private final BookRepository bookRepository;

    public BookRepositoryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookItem bookitem) {
        bookRepository.save(bookitem);
        addOneBookToBookCountingVariable();
    }

    private void addOneBookToBookCountingVariable() {
        countBooks++;
    }

    public void deleteBookUsingId(UUID id) {
        bookRepository.removeById(id);
        countBooks--;
    }

    public int countBooks() {
        return countBooks;
    }

    public List<BookItem> searchByAuthor(String authorName) {
        return bookRepository.searchByAuthor(authorName);
    }

    public List<BookItem> searchByYear(int publicationYear) {
        return bookRepository.searchByYear(publicationYear);
    }


    public List<BookItem> searchByCategory(Book.SubjectCategory category) {
        return bookRepository.searchByCategory(category);
    }

    public List<BookItem> searchByTitle(String bookTitle) {
        return bookRepository.searchByTitle(bookTitle);
    }

    public BookItem searchBookById(UUID bookId){
        return bookRepository.get(bookId);
    }

    @Override
    public String toString() {
        return "BookRepositoryService{" +
                "bookRepository=" + bookRepository +
                '}';
    }
}

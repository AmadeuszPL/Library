package com.amadeusz.library.application;

import java.util.*;

public class BookItemService {

    private final BookItemRepository bookRepository;

    public BookItemService(BookItemRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(BookItem bookitem) {
        bookRepository.save(bookitem);
    }

    public void deleteBookUsingId(UUID id) {
        bookRepository.removeById(id);
    }

/*    public List<BookItem> searchByAuthor(String authorName) {
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
    }*/

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

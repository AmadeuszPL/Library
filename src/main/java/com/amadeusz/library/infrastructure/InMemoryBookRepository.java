package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.Book;
import com.amadeusz.library.application.BookItem;
import com.amadeusz.library.application.BookRepository;

import java.util.*;
import java.util.stream.Collectors;


public class InMemoryBookRepository implements BookRepository {

    public static int countBooks;
    private Map<UUID, BookItem> bookRepository;

    public InMemoryBookRepository() {
        this.bookRepository = new HashMap<>();
    }


    @Override
    public void save(BookItem bookitem) {
        bookRepository.put(bookitem.getId(), bookitem);
    }

    @Override
    public void removeById(UUID bookId) {
        bookRepository.remove(bookId);
    }

    @Override
    public BookItem get(UUID bookId) {
        return bookRepository.get(bookId);
    }

    @Override
    public String toString() {
        return "InMemoryBookRepository{\n" + bookRepository.values() + '}';
    }

    public List<BookItem> searchByTitle(String title) {
        List<BookItem> booksByTitle = new ArrayList<>();
        for (Map.Entry<UUID, BookItem> repositoryEntry : bookRepository.entrySet()) {
            if (repositoryEntry.getValue().getTitle().contains(title)) {
                booksByTitle.add(repositoryEntry.getValue());
            }
        }
        return booksByTitle;
    }

    public List<BookItem> searchByCategory(Book.SubjectCategory subjectCategory) {
        return bookRepository.values().stream()
                .filter(value -> value.getCategory().equals(subjectCategory))
                .sorted(Comparator.comparing(BookItem::getTitle).reversed())
                .collect(Collectors.toList());
    }

    public List<BookItem> searchByAuthor(String author) {
        List<BookItem> booksByAuthor = new ArrayList<>();
        for (Map.Entry<UUID, BookItem> repositoryEntry : bookRepository.entrySet()) {
            if (repositoryEntry.getValue().getAuthor().equals(author)) {
                booksByAuthor.add(repositoryEntry.getValue());
            }
        }
        Collections.sort(booksByAuthor);
        return booksByAuthor;
    }

    public List<BookItem> searchByYear(int year) {
        return bookRepository.values().stream()
                .filter(value -> value.getYear() == year)
                .sorted(Comparator.comparing(BookItem::getTitle))
                .collect(Collectors.toList());
    }


}

package com.amadeusz.library;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class InMemoryBookRepository implements BookRepository {

    private static int countBooks;
    private Map<UUID, BookItem> repository;

    InMemoryBookRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void addBook(BookItem bookitem) {
        repository.put(bookitem.getId(), bookitem);
        countBooks++;
    }

    @Override
    public void deleteBookUsingId(UUID id) {
        repository.remove(id);
        countBooks--;
    }

    @Override
    public int countBooks() {
        return countBooks;
    }

    @Override
    public Map<UUID, BookItem> searchByTitle(String title) {
        Map<UUID, BookItem> booksByTitle = new HashMap<>();
        for (Map.Entry<UUID, BookItem> repositoryEntry : repository.entrySet()) {
            if (repositoryEntry.getValue().getTitle().contains(title)) {
                booksByTitle.put(repositoryEntry.getKey(), repositoryEntry.getValue());
            }
        }
        return booksByTitle;
    }

    @Override
    public Map<UUID, BookItem> searchByCategory(Book.SubjectCategory subjectCategory) {
        Map<UUID, BookItem> booksByCategory = new HashMap<>();
        repository.entrySet().stream()
                .filter(entry -> entry.getValue().getCategory().equals(subjectCategory))
                .forEach(entry -> booksByCategory.put(entry.getKey(),
                        entry.getValue()));
        return booksByCategory;
    }

    @Override
    public Map<UUID, BookItem> searchByAuthor(String author) {
        Map<UUID, BookItem> booksByAuthor = new HashMap<>();
        for (Map.Entry<UUID, BookItem> repositoryEntry : repository.entrySet()) {
            if (repositoryEntry.getValue().getAuthor().equals(author)) {
                booksByAuthor.put(repositoryEntry.getKey(),
                        repositoryEntry.getValue());
            }
        }
        return booksByAuthor;
    }

    @Override
    public Map<UUID, BookItem> searchByYear(int year) {
        Map<UUID, BookItem> booksByYear = new HashMap<>();
        repository.entrySet().stream()
                .filter(entry -> (entry.getValue().getYear() == year))
                .forEach(entry -> booksByYear.put(entry.getKey(), entry.getValue()));
        return booksByYear;
    }

    @Override
    public Optional<BookItem> searchById(UUID bookId) {
        return repository.values().stream()
                .filter(bookItem -> bookItem.getId().equals(bookId))
                .findFirst();
    }

    @Override
    public String toString() {
        return "InMemoryBookRepository{\n" + repository.values() + '}';
    }

}

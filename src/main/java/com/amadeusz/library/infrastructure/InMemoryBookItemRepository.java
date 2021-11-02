package com.amadeusz.library.infrastructure;

import com.amadeusz.library.application.BookItem;
import com.amadeusz.library.application.BookItemRepository;

import java.util.*;


public class InMemoryBookItemRepository implements BookItemRepository {

    //private final BookItemEntityMapper mapper;
    private final Map<UUID, BookItemEntity> bookRepository;

    public InMemoryBookItemRepository() {
//        this.mapper = ....;
        this.bookRepository = new HashMap<>();
    }


    @Override
    public int getBooksCount() {
        return bookRepository.size();
    }

    @Override
    public void save(BookItem bookitem) {
        //bookRepository.put(bookitem.getId(), mapper.map(bookitem));
    }

    @Override
    public void removeById(UUID bookId) {
        bookRepository.remove(bookId);
    }

    @Override
    public BookItem get(UUID bookId) {
        //return mapper.map(bookRepository.get(bookId));
        return null;
    }

    @Override
    public String toString() {
        return "InMemoryBookRepository{\n" + bookRepository.values() + '}';
    }

/*
    public List<BookItem> searchByTitle(String title) {
        List<BookItem> booksByTitle = new ArrayList<>();
        for (Map.Entry<UUID, BookItemEntity> repositoryEntry :
                bookRepository.entrySet()) {
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
*/

}

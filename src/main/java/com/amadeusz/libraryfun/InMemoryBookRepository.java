package com.amadeusz.libraryfun;

import javax.lang.model.type.ArrayType;
import java.util.*;

public class InMemoryBookRepository implements BookRepository {

    public static int countBooks;
    private Map<String, List> bookTitles = new HashMap<>();
    private Map<String, List> bookAuthors = new HashMap<>();
    private Map<SubjectCategory, List> bookCategories = new HashMap<>();
    private Map<Integer, List> bookPublicationYear = new HashMap<>();
    private BookItem temporaryBookItem;


    public void updateRepository(BookItem bookItem) {
        temporaryBookItem = bookItem;
        updateBookTitles();
        updateBookAuthors();
        updateBookCategories();
        updateBookPublicationYear();
    }

    private void updateBookPublicationYear() {
        if (bookPublicationYear.containsKey(temporaryBookItem.getPublicationYear())) {
            List list = bookPublicationYear.get(temporaryBookItem.getPublicationYear());
            list.add(temporaryBookItem);
        } else {
            bookPublicationYear.put(temporaryBookItem.getPublicationYear(),
                    new ArrayList<>(Arrays.asList(temporaryBookItem)));
        }
    }

    private void updateBookCategories() {
        if (bookCategories.containsKey(temporaryBookItem.getCategory())) {
            List list = bookCategories.get(temporaryBookItem.getCategory());
            list.add(temporaryBookItem);
        } else {
            bookCategories.put(temporaryBookItem.getCategory(),
                    new ArrayList<>(Arrays.asList(temporaryBookItem)));
        }
    }

    private void updateBookAuthors() {
        if (bookAuthors.containsKey(temporaryBookItem.getAuthor())) {
            List list = bookAuthors.get(temporaryBookItem.getAuthor());
            list.add(temporaryBookItem);
        } else {
            bookAuthors.put(temporaryBookItem.getAuthor().getName(),
                    new ArrayList<>(Arrays.asList(temporaryBookItem)));
        }
    }

    private void updateBookTitles() {
        if (bookTitles.containsKey(temporaryBookItem.getTitle())) {
            List list = bookTitles.get(temporaryBookItem.getTitle());
            list.add(temporaryBookItem);
        } else {
            bookTitles.put(temporaryBookItem.getTitle(),
                    new ArrayList<>(Arrays.asList(temporaryBookItem)));
        }
    }

    private void updateTitlesList() {
    }

    @Override
    public void searchByTitle(String title) {
        System.out.println(bookTitles.get(title));
    }

    @Override
    public void searchByCategory(SubjectCategory category) {
        System.out.println(bookCategories.get(category));
    }

    @Override
    public void searchByAuthor(String author) {
        System.out.println(bookAuthors.get(author));
    }

    @Override
    public void searchByYear(int year) {
        System.out.println(bookPublicationYear.get(year));
    }

    public Map<Double, BookItem> getBooks() {
        List<BookItem> books = new ArrayList<>();
        bookTitles.entrySet()
                .stream()
                .map(BookItem -> BookItem.getValue())
                .forEach(books::addAll);

        Map<Double, BookItem> barcodeMap = new HashMap<>();

        for(BookItem book : books){
            barcodeMap.put(book.getBarcode(), book);
        }

        return barcodeMap;
    }

    /*    @Override
    public void searchByTitle(String string) {
        for (Map.Entry<String, BookItem> entry : db.entrySet()){
            if (entry.getValue().getTitle().contains(string)){
                System.out.println(entry.getKey() + " " + entry.getValue().getTitle());
            }
        }
    }

    @Override
    public void searchByCategory(SubjectCategory category) {
        db.entrySet()
                .stream()
                .filter(BookItem -> BookItem.getValue().getCategory().equals(category))
                .map(BookItem -> (BookItem.getKey() + " " + BookItem.getValue().getTitle()))
                .forEach(System.out::println);
    }

    @Override
    public void searchByAuthor(String author) {
        db.entrySet()
                .stream()
                .filter(BookItem -> BookItem.getValue().getAuthor().getName().equals(author))
                .map(BookItem -> (BookItem.getKey() + " " + BookItem.getValue().getTitle()))
                .forEach(System.out::println);
    }

    @Override
    public void searchByYear(int year) {
        List<BookItem> bookItems = new ArrayList<>();
        for (BookItem book : db.values()){
            if (book.getPublicationYear() == year){
                bookItems.add(book);
            }
        }
        Set<String> keys = new HashSet<>();
        for(BookItem bookItem : bookItems){
            keys.add(bookItem.getISBN());
        }
        for(String key : keys){
            System.out.println(key + " " + db.get(key).getTitle());
        }
    }

 */
}

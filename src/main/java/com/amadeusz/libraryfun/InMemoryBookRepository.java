package com.amadeusz.libraryfun;


import java.util.*;
//import org.apache.commons.lang3.*;

public class InMemoryBookRepository implements BookRepository{

    private Map<String, BookItem> db = new HashMap<>();

    public Map<String, BookItem> getDb() {
        return db;
    }

    @Override
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
}

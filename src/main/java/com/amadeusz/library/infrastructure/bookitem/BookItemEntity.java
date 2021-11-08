package com.amadeusz.library.infrastructure.bookitem;

import com.amadeusz.library.application.bookitem.BookItem;

import java.util.UUID;

public class BookItemEntity {

    private UUID id;
    private String bookIsbn;
    private String rackNumber;
    private String bookItemStatus;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(String rackNumber) {
        this.rackNumber = rackNumber;
    }

    public String getBookItemStatus() {
        return bookItemStatus;
    }

    public void setBookItemStatus(String bookItemStatus) {
        this.bookItemStatus = bookItemStatus;
    }

    @Override
    public String toString() {
        return "BookItemEntity{" +
                "id=" + id +
                ", bookIsbn='" + bookIsbn + '\'' +
                ", bookItemStatus='" + bookItemStatus + '\'' +
                "}\n";
    }
}

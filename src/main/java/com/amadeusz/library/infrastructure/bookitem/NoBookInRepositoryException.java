package com.amadeusz.library.infrastructure.bookitem;

public class NoBookInRepositoryException extends NullPointerException {
    public NoBookInRepositoryException(String message) {
        super(message);
    }
}

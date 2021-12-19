package com.amadeusz.library.exceptions;

public class NoBookInRepositoryException extends NullPointerException {
    public NoBookInRepositoryException(String message) {
        super(message);
    }
}

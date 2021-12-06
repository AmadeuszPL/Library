package com.amadeusz.library.application.exceptions;

public class NoBookInRepositoryException extends NullPointerException {
    public NoBookInRepositoryException(String message) {
        super(message);
    }
}

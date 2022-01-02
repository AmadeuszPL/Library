package com.amadeusz.library.application.exceptions;

public class NoInRepositoryException extends NullPointerException {
    public NoInRepositoryException(String message) {
        super(message);
    }
}

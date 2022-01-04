package com.amadeusz.library.application.model.book;

import lombok.Data;

@Data
public class Author {

    private final String name;
    private final int birthYear;

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}

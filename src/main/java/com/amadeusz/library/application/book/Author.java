package com.amadeusz.library.application.book;


public class Author {

    private Long id;
    private final String name;
    private final int birthYear;


    public Author(String name, int birthYear) {
        this.name = name;
        this.birthYear = birthYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", birthYear=" + birthYear +
                '}';
    }
}

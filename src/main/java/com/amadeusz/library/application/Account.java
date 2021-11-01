package com.amadeusz.library.application;

import java.util.UUID;

public abstract class Account {

    private final UUID id;
    private final String login;
    private final String password;
    private final Person person;

    public Account(String login, String password, Person person) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", person=" + person +
                "\n}";
    }
}

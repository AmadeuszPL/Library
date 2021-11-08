package com.amadeusz.library.application.accounts;

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

    public Account(UUID id, String login, String password, Person person) {
        this.id = id;
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

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
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

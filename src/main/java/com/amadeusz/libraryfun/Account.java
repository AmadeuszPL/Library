package com.amadeusz.libraryfun;

public abstract class Account {

    private String login;
    private String password;
    private Person person;

    public Account(String login, String password, Person person) {
        this.login = login;
        this.password = password;
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", person=" + person +
                '}';
    }
}

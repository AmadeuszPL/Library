package com.amadeusz.library.application.model.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
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

    @Override
    public String toString() {
        return "Account{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", person=" + person +
                "\n}";
    }

    public static void main(String[] args) {
    }
}

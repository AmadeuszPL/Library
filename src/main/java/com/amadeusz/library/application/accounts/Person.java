package com.amadeusz.library.application.accounts;

public class Person {

    private final String name;
    private final Address adress;
    private final String email;
    private final String telephoneNumber;

    public Person(String name, Address adress, String eMail, String telephoneNumber) {
        this.name = name;
        this.adress = adress;
        this.email = eMail;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public Address getAdress() {
        return adress;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @Override
    public String toString() {
        return '\'' + name + '\'';
    }

}

package com.amadeusz.library.application;

public class Person {

    private String name;
    private Address adress;
    private String eMail;
    private String telephoneNumber;

    public Person(String name, Address adress, String eMail, String telephoneNumber) {
        this.name = name;
        this.adress = adress;
        this.eMail = eMail;
        this.telephoneNumber = telephoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    @Override
    public String toString() {
        return '\'' + name + '\'';
    }

}

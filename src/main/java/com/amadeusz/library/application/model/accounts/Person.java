package com.amadeusz.library.application.model.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private final String name;
    private Address address;
    private final String email;
    private final String telephoneNumber;
/*
    public Person(@JsonProperty("name") String name,
                  @JsonProperty("email") String eMail,
                  @JsonProperty("telephoneNumber") String telephoneNumber) {
        this.name = name;
        this.email = eMail;
        this.telephoneNumber = telephoneNumber;
    }*/

    public Person(String name, Address address, String eMail, String telephoneNumber) {
        this.name = name;
        this.address = address;
        this.email = eMail;
        this.telephoneNumber = telephoneNumber;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return '\'' + name + '\'';
    }

}

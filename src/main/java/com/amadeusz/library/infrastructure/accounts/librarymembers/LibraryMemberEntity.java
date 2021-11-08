package com.amadeusz.library.infrastructure.accounts.librarymembers;

import com.amadeusz.library.application.accounts.Address;

import java.math.BigDecimal;
import java.util.UUID;

public class LibraryMemberEntity {

    private UUID id;
    private String login;
    private String password;
    private String personName;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    private String personEmail;
    private String personTelephoneNumber;
    private int totalBooksCheckedOut;
    private BigDecimal fine;
    private long creditCardNumber;
    private String cardTypeToString;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getPersonTelephoneNumber() {
        return personTelephoneNumber;
    }

    public void setPersonTelephoneNumber(String personTelephoneNumber) {
        this.personTelephoneNumber = personTelephoneNumber;
    }

    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    public void setTotalBooksCheckedOut(int totalBooksCheckedOut) {
        this.totalBooksCheckedOut = totalBooksCheckedOut;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardTypeToString() {
        return cardTypeToString;
    }

    public void setCardTypeToString(String cardTypeToString) {
        this.cardTypeToString = cardTypeToString;
    }

    @Override
    public String toString() {
        return "\nLibraryMemberEntity{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", personName='" + personName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", country='" + country + '\'' +
                ", personEmail='" + personEmail + '\'' +
                ", personTelephoneNumber='" + personTelephoneNumber + '\'' +
                ", totalBooksCheckedOut=" + totalBooksCheckedOut +
                ", fine=" + fine +
                ", creditCardNumber=" + creditCardNumber +
                ", cardTypeToString='" + cardTypeToString + '\'' +
                "}\n";
    }
}

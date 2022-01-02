package com.amadeusz.library.application.model.accounts.librarymembers;

import com.amadeusz.library.application.model.accounts.Account;
import com.amadeusz.library.application.model.accounts.Person;
import com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality.CreditCard;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public class LibraryMember extends Account {

    private int totalBooksCheckedOut;
    private BigDecimal fine;
    private CreditCard card;


    public LibraryMember(@JsonProperty("login") String login,
                         @JsonProperty("password") String password,
                         @JsonProperty("person") Person person) {
        super(login, password, person);
    }

    public LibraryMember(UUID accountId, String login, String password,
                         Person person, BigDecimal fine,
                         CreditCard creditCard, int totalBooksCheckedOut) {
        super(accountId, login, password, person);
        this.fine = fine;
        this.card = creditCard;
        this.totalBooksCheckedOut = totalBooksCheckedOut;
    }

    public LibraryMember(UUID accountId, String login, String password,
                         Person person, BigDecimal fine, int totalBooksCheckedOut) {
        super(accountId, login, password, person);
        this.fine = fine;
        this.totalBooksCheckedOut = totalBooksCheckedOut;
    }

    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    public void addOneBook() {
        totalBooksCheckedOut++;
    }

    public void subtractOneBook() {
        totalBooksCheckedOut--;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void addFine(BigDecimal fine) {
        if (this.fine == null) {
            this.fine = fine;
        } else {
            this.fine = this.fine.add(fine);
        }
    }

    public void sendNotificationBookAvailable(UUID bookId) {
        System.out.println("Sending message: \n Book with id: " + bookId +
                " is avaliable to rent. \nto email adress: " + this.getPerson().getEmail());
    }

    public void sendNotificationBookReturnedAfterTime(UUID bookId) {
        System.out.println("Sending message: \n Book with id: " + bookId +
                " was returned after time" + this.getPerson().getEmail());
    }

    public CreditCard getCard() {
        return card;
    }

    public void clearFine() {
        this.fine = BigDecimal.valueOf(0);
    }


    public void addCreditCard(CreditCard creditCard) {
        this.card = creditCard;
    }

}

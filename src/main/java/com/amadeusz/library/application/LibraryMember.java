package com.amadeusz.library.application;

import java.math.BigDecimal;
import java.util.UUID;

public class LibraryMember extends Account {

    private int totalBooksCheckedOut;
    private BigDecimal fine;
    private CreditCard card;

    public LibraryMember(String login, String password, Person person) {
        super(login, password, person);
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
        if (this.fine == null){
            this.fine = fine;
        } else {
            this.fine = this.fine.add(fine);
        }
    }

    public void sendNotificationBookAvailable(UUID bookId){
        System.out.println("Sending message: \n Book with id: " + bookId +
                "is avaliable to rent to email adress" + this.getPerson().geteMail());
    }

    public void sendNotificationBookReturnedAfterTime(UUID bookId){
        System.out.println("Sending message: \n Book with id: " + bookId +
                " was returned after time" + this.getPerson().geteMail());
    }

    public CreditCard getCard() {
        return card;
    }

    public void clearFine() {
        this.fine = BigDecimal.valueOf(0);
    }


    public void addCreditCard(long cardNumber){
        this.card = new CreditCard(cardNumber);
    }

}

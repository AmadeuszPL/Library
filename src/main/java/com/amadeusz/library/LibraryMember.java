package com.amadeusz.library;

import java.math.BigDecimal;

class LibraryMember extends Account {

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

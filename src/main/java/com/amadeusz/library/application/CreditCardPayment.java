package com.amadeusz.library.application;

import java.math.BigDecimal;

public class CreditCardPayment extends FinePayment implements CreditCardPaymentFunctionality {

    public CreditCardPayment(LibraryMember libraryMember, FinePaymentMethod finePaymentMethod) throws IllegalAccessException {
        super(libraryMember, finePaymentMethod);
        makeCreditCardPayment(libraryMember.getFine(),
                libraryMember.getCard());
    }

    public static CreditCardPayment of(LibraryMember libraryMember) throws IllegalAccessException {
        return new CreditCardPayment(libraryMember, FinePaymentMethod.CREDIT_CARD);
    }

    public void makeCreditCardPayment(BigDecimal fine, CreditCard card) throws IllegalAccessException {
        if (!(Math.random() > 0.2)) {
            throw new IllegalAccessException("CreditCard transaction denied.");
        }
        if (card == null){
            throw new IllegalAccessException("You have not added any card to " +
                    "user.");
        }
    }
}

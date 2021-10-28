package com.amadeusz.library;

import java.math.BigDecimal;

class CreditCardPayment extends FinePayment implements CreditCardPaymentFunctionality {

    public CreditCardPayment(LibraryMember libraryMember, FinePaymentMethod finePaymentMethod) throws IllegalAccessException {
        super(libraryMember, finePaymentMethod);
        makeCreditCardPayment(libraryMember.getFine());
    }

    static CreditCardPayment of(LibraryMember libraryMember) throws IllegalAccessException {
        return new CreditCardPayment(libraryMember, FinePaymentMethod.CREDIT_CARD);
    }

    public void makeCreditCardPayment(BigDecimal fine) throws IllegalAccessException {
        if (!(Math.random() > 0.2)) {
            throw new IllegalAccessException("CreditCard transaction denied.");
        }
    }
}

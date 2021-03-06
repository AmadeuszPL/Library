package com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality;

import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class FinePayment {

    private final FinePaymentMethod finePaymentMethod;
    private final UUID transactionUUID;
    private final BigDecimal value;

    public FinePayment(LibraryMember libraryMember, FinePaymentMethod finePaymentMethod) {
        this.finePaymentMethod = finePaymentMethod;
        this.transactionUUID = UUID.randomUUID();
        this.value = libraryMember.getFine();
        libraryMember.clearFine();
    }

    @Override
    public String toString() {
        return "Payment{" +
                "finePaymentMethod=" + finePaymentMethod +
                ", transactionUUID=" + transactionUUID +
                ", value=" + value +
                '}';
    }

    enum FinePaymentMethod {
        CASH,
        CREDIT_CARD
    }
}

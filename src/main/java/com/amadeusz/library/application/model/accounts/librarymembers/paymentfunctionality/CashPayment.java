package com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality;

import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;

class CashPayment extends FinePayment {

    public CashPayment(LibraryMember libraryMember, FinePaymentMethod finePaymentMethod) {
        super(libraryMember, finePaymentMethod);
    }

    static CashPayment of(LibraryMember libraryMember) {
        return new CashPayment(libraryMember, FinePaymentMethod.CASH);
    }
}

package com.amadeusz.library;

class CashPayment extends FinePayment {

    public CashPayment(LibraryMember libraryMember, FinePaymentMethod finePaymentMethod) {
        super(libraryMember, finePaymentMethod);
    }

    static CashPayment of(LibraryMember libraryMember) {
        return new CashPayment(libraryMember, FinePaymentMethod.CASH);
    }
}

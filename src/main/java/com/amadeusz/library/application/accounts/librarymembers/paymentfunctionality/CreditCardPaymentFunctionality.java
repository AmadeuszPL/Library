package com.amadeusz.library.application.accounts.librarymembers.paymentfunctionality;

import java.math.BigDecimal;

public interface CreditCardPaymentFunctionality {

    void makeCreditCardPayment(BigDecimal fine, CreditCard card) throws IllegalAccessException;

}

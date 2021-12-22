package com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality;

import java.math.BigDecimal;

public interface CreditCardPaymentFunctionality {

    void makeCreditCardPayment(BigDecimal fine, CreditCard card) throws IllegalAccessException;

}

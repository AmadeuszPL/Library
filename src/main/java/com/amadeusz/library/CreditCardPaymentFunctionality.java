package com.amadeusz.library;

import java.math.BigDecimal;

public interface CreditCardPaymentFunctionality {

    void makeCreditCardPayment(BigDecimal fine) throws IllegalAccessException;

}

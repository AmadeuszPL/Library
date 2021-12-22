package com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality;

import java.util.regex.Pattern;

public enum CardType {

    UNKNOWN,
    VISA("4([0-9]{12})|4([0-9]{15})"),
    AMEX("(34|37)([0-9]{13})"),
    MASTERCARD("(51|52|53|54|55)([0-9]{14})");

    private final Pattern pattern;

    CardType() {
        this.pattern = null;
    }

    CardType(String pattern) {
        this.pattern = Pattern.compile(pattern);
    }

    public static CardType detect(String cardNumber) {

        for (CardType cardType : CardType.values()) {
            if (cardType.hasMatchAndIsNotUnknown(cardNumber)) {
                return cardType;
            }
        }
        return UNKNOWN;
    }

    private boolean hasMatchAndIsNotUnknown(String cardNumber) {
        return pattern != null && pattern.matcher(cardNumber).matches();
    }
}

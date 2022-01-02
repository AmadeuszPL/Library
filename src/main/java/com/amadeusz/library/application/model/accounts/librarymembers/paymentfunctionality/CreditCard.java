package com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditCard {

    private final long cardNumber;
    private final CardType cardType;

    public CreditCard(@JsonProperty("cardNumber") long cardNumber) {
        validate(cardNumber);
        this.cardType = CardType.detect(String.valueOf(cardNumber));
        if (cardType == CardType.UNKNOWN) {
            throw new IllegalArgumentException("Wrong credit card. Unknown " +
                    "type.");
        }
        this.cardNumber = cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public long getCardNumber() { return cardNumber; }

    private void validate(long cardNumber) {

        preValidate(cardNumber);
        validateWithLuhnAlgorithm(cardNumber);

    }

    private void preValidate(long cardNumber) {

        if (String.valueOf(cardNumber).length() < 13
                || String.valueOf(cardNumber).length() > 16) {
            throw new IllegalArgumentException("This is not a Credit Card " +
                    "Number");
        }

    }

    private static void validateWithLuhnAlgorithm(Long cardNumber) {

        int verifySum = 0;
        int secondNumberFromEnd;
        int miniSum;
        int cardNumberLenght = String.valueOf(cardNumber).length();

        for (int i = 0; i < cardNumberLenght; i += 2) {
            secondNumberFromEnd = (int) ((cardNumber % 100
                    - cardNumber % 10) / 10);
            miniSum = (secondNumberFromEnd * 2) / 10
                    + (secondNumberFromEnd * 2) % 10;
            verifySum += miniSum + (int) (cardNumber % 10);
            cardNumber = (cardNumber - cardNumber % 100) / 100;
        }
        if (verifySum % 10 != 0) {
            throw new IllegalArgumentException("This is not a Credit Card " +
                    "Number.");
        }

    }

}

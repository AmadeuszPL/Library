package com.amadeusz.library.application.book;

import com.amadeusz.library.exceptions.ISBNValidationException;
import org.apache.commons.lang3.math.NumberUtils;

public class ISBN {

    private final String value;

    private ISBN(String value) {
        value = removeUnwantedChars(value);
        validate(value);
        this.value = value;
    }

    private String removeUnwantedChars(String value) {
        value = value.replaceAll("[^0-9]", "");
        return value;
    }

    public static ISBN of(String value) {
        return new ISBN(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ISBN{" +
                "value='" + value + '\'' +
                '}';
    }

    private void validate(String value) {

        if (value.length() != 10 && value.length() != 13) {
            throw new RuntimeException("Wrong ISBN lenght");
        }

        if (value.length() == 10) {
            validateISBN10(value);
        } else {
            validateISBN13(value);
        }
    }

    private void validateISBN10(String isbn) {

        if (!NumberUtils.isParsable(isbn) &&
                !(isbn.charAt(9) == 'X' && NumberUtils.isParsable(isbn.substring(0, 8)))) {
            throw new RuntimeException("ISBN is not a number");
        }
        int test_value = 0;

        for (int i = 1; i < 10; i++) {
            int j = Character.getNumericValue(isbn.charAt(i - 1));
            test_value += i * j;
        }

        if (isbn.charAt(9) == 'X') {
            test_value += 100;
        } else {
            test_value += 10 * Character.getNumericValue(isbn.charAt(9));
        }

        finalISBNtest(test_value, 11);
    }

    private void validateISBN13(String isbn) {

        if (!NumberUtils.isParsable(isbn)) {
            throw new ISBNValidationException("ISBN is not a number");
            //how to throw exceptions change
        }

        int test_value = 0;

        for (int i = 1; i < 14; i++) {
            int j = Character.getNumericValue(isbn.charAt(i - 1));
            int x;
            if (i % 2 == 0) {
                x = 3;
            } else {
                x = 1;
            }
            test_value += x * j;
        }

        finalISBNtest(test_value, 10);
    }

    private void finalISBNtest(int test_value, int divided) {

        if (test_value % divided != 0) {
            throw new RuntimeException("ISBN is not valid");
        }
    }

}

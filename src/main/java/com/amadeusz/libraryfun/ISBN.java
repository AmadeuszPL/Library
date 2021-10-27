package com.amadeusz.libraryfun;

import org.apache.commons.lang3.math.NumberUtils;

class ISBN {

    private final String value;

    private ISBN(String value) {
        validate(value);
        value = value.replace("-", "");
        value = value.replace(" ", "");
        this.value = value;
    }

    static ISBN of(String value) {
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

        value = value.replace("-", "");
        value = value.replace(" ", "");

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
            throw new RuntimeException("ISBN is not a number");
        }

        int test_value = 0;

        for (int i = 1; i < 14; i++) {
            int j = Character.getNumericValue(isbn.charAt(i - 1));
            int x = 0;
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

package com.amadeusz.library.application.model.accounts;

import lombok.Data;

@Data
public class Address {

    private final String street;
    private final String city;
    private final String zipCode;
    private final String country;

}

package com.amadeusz.library.application.model.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {

    private final String name;
    private Address address;
    private final String email;
    private final String telephoneNumber;

}

package com.amadeusz.library.application.service;

import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality.CreditCard;

import java.util.UUID;

public interface LibraryMembersService {

    LibraryMember add(LibraryMember libraryMember);

    LibraryMember getById(UUID id);

    void removeById(UUID id);

    LibraryMember update(LibraryMember libraryMember);

    CreditCard addCreditCardToUser(UUID id, CreditCard creditCard);

}

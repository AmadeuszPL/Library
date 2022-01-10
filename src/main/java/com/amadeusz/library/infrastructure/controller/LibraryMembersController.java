package com.amadeusz.library.infrastructure.controller;

import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.application.model.accounts.librarymembers.paymentfunctionality.CreditCard;
import com.amadeusz.library.application.service.LibraryMembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/members")
public class LibraryMembersController {

    @Autowired
    private LibraryMembersService libraryMembersService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryMember addLibraryMember(@Valid @RequestBody final LibraryMember libraryMember) {
        return libraryMembersService.add(libraryMember);
    }

    @GetMapping("/{id}")
    public LibraryMember getLibraryMemberById(@PathVariable UUID id) {
        return libraryMembersService.getById(id);
    }

    @PostMapping("/{id}")
    public CreditCard addCreditCard(@PathVariable UUID id, @Valid @RequestBody final CreditCard creditCard) {
        return libraryMembersService.addCreditCardToUser(id, creditCard);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable UUID id) {
        libraryMembersService.removeById(id);
    }

}

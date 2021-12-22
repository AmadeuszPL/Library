package com.amadeusz.library.application.service;

import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.infrastructure.repository.LibraryMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

//@Service("accountsService")
public class DefaultAccountsService implements AccountsService {

    private final LibraryMembersRepository libraryMembersRepository;

    @Autowired


    public DefaultAccountsService(LibraryMembersRepository libraryMembersRepository) {
        this.libraryMembersRepository = libraryMembersRepository;
    }

    @Override
    public void addLibraryMember(LibraryMember libraryMember) {
        libraryMembersRepository.create(libraryMember);
    }

    public LibraryMember searchByLibraryMemberId(UUID libraryMemberId) {
        return libraryMembersRepository.readByLibraryMemberId(libraryMemberId);
    }

    @Override
    public void deleteLibraryMember(LibraryMember libraryMember) {
        libraryMembersRepository.remove(libraryMember);

    }
}

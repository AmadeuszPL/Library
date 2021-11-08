package com.amadeusz.library.application.accounts;

import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.application.accounts.librarymembers.LibraryMembersRepository;

import java.util.UUID;

public class DefaultAccountsService implements AccountsService {

    private final LibraryMembersRepository libraryMembersRepository;

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

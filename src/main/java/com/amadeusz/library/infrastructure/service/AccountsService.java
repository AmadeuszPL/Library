package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;

import java.util.UUID;

public interface AccountsService {

    void addLibraryMember(LibraryMember libraryMember);

    LibraryMember searchByLibraryMemberId(UUID libraryMemberId);

    void deleteLibraryMember(LibraryMember libraryMember);

}

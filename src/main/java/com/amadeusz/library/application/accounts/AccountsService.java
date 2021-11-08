package com.amadeusz.library.application.accounts;

import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;

import java.util.UUID;

public interface AccountsService {

    void addLibraryMember(LibraryMember libraryMember);

    LibraryMember searchByLibraryMemberId(UUID libraryMemberId);

    void deleteLibraryMember(LibraryMember libraryMember);

}

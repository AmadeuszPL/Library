package com.amadeusz.library.application.accounts.librarymembers;

import com.amadeusz.library.application.accounts.Account;

import java.util.UUID;

public interface LibraryMembersRepository {

    void create(LibraryMember libraryMember);

    LibraryMember readByLibraryMemberId(UUID libraryMemberId);

    void update(LibraryMember libraryMember);

    void remove(LibraryMember libraryMember);

}

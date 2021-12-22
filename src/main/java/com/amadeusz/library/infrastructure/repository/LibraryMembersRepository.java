package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;

import java.util.UUID;

public interface LibraryMembersRepository {

    void create(LibraryMember libraryMember);

    LibraryMember readByLibraryMemberId(UUID libraryMemberId);

    void update(LibraryMember libraryMember);

    void remove(LibraryMember libraryMember);

}

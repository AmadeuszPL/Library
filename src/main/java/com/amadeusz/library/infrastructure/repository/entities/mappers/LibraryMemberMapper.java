package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.infrastructure.repository.entities.LibraryMemberEntity;

public interface LibraryMemberMapper {

    LibraryMemberEntity map(LibraryMember libraryMember);

    LibraryMember map(LibraryMemberEntity libraryMemberEntity);

}

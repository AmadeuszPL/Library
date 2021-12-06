package com.amadeusz.library.infrastructure.model.mappers;

import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.infrastructure.model.LibraryMemberEntity;

public interface LibraryMemberMapper {

    LibraryMemberEntity map(LibraryMember libraryMember);
    LibraryMember map(LibraryMemberEntity libraryMemberEntity);

}

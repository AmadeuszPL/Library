package com.amadeusz.library.infrastructure.accounts.librarymembers;

import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;

public interface LibraryMemberMapper {

    LibraryMemberEntity map(LibraryMember libraryMember);
    LibraryMember map(LibraryMemberEntity libraryMemberEntity);

}

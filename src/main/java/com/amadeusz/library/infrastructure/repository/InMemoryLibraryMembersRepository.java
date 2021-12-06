package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.application.exceptions.IllegalOperationException;
import com.amadeusz.library.infrastructure.model.LibraryMemberEntity;
import com.amadeusz.library.infrastructure.model.mappers.DefaultLibraryMemberMapper;
import com.amadeusz.library.infrastructure.model.mappers.LibraryMemberMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;


public class InMemoryLibraryMembersRepository implements LibraryMembersRepository {

    private final LibraryMemberMapper mapper;
    private final Map<UUID, LibraryMemberEntity> usersRepository;

    public InMemoryLibraryMembersRepository() {
        this.mapper = new DefaultLibraryMemberMapper();
        this.usersRepository = new HashMap<>();
    }


    @Override
    public void create(LibraryMember libraryMember) {
        usersRepository.put(libraryMember.getId(), mapper.map(libraryMember));
    }

    @Override
    public LibraryMember readByLibraryMemberId(UUID libraryMemberId) {
        Optional<LibraryMemberEntity> memberEntity =
                Optional.ofNullable(usersRepository.get(libraryMemberId));
        if (memberEntity.isEmpty()) {
            throw new IllegalOperationException("No member in database.");
        }
        return mapper.map(memberEntity.get());
    }

    @Override
    public void update(LibraryMember libraryMember) {
        usersRepository.computeIfPresent(libraryMember.getId(),
                (k, v) -> mapper.map(libraryMember));
    }

    @Override
    public void remove(LibraryMember libraryMember) {
        usersRepository.remove(libraryMember.getId());
    }
}

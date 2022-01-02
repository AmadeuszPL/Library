package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.infrastructure.repository.entities.LibraryMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAccountsJpaRepository extends JpaRepository<LibraryMemberEntity, UUID> {

}

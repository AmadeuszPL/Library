package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.infrastructure.repository.entities.BookLendingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookLendingJpaRepository extends JpaRepository<BookLendingEntity, UUID> {

    BookLendingEntity findByBookItemId(UUID id);

}

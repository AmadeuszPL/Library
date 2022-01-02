package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.infrastructure.repository.entities.BookItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookItemJpaRepository extends JpaRepository<BookItemEntity, UUID> {

    Page<BookItemEntity> findByBookIsbn(String isbn, Pageable pageable);

    List<BookItemEntity> findByBookIsbnAndBookItemStatus(String isbn, String status);

}

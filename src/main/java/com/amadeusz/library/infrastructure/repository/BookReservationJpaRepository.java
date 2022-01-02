package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.infrastructure.repository.entities.BookReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookReservationJpaRepository extends JpaRepository<BookReservationEntity, UUID> {

    List<BookReservationEntity> findByBookIsbnAndReservationStatusOrderByIssueDateAsc(String isbn, String status);

}

package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.infrastructure.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity, String> {

    Page<BookEntity> findAll(Pageable pageable);

    Page<BookEntity> findByTitleLike(String title, Pageable pageable);

    Page<BookEntity> findByPublicationYear(int year, Pageable pageable);

    Page<BookEntity> findByAuthorName(String name, Pageable pageable);

    Page<BookEntity> findByCategory(String category, Pageable pageable);

}

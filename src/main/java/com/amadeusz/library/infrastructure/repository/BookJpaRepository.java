package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.infrastructure.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, String> {

    Page<BookEntity> findByTitleLike(String title, Pageable pageable);

    Page<BookEntity> findByPublicationYear(int year, Pageable pageable);

    Page<BookEntity> findByAuthorName(String name, Pageable pageable);

    Page<BookEntity> findByCategory(String category, Pageable pageable);

    Page<BookEntity> findAll(Pageable pageable);

    void deleteByIsbn(String isbn);

}

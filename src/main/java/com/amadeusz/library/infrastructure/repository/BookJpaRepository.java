package com.amadeusz.library.infrastructure.repository;

import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.infrastructure.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookJpaRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByTitle(String title);

    List<BookEntity> findByPublicationYear(int year);

    List<BookEntity> findByAuthorName(String name);

    List<BookEntity> findByCategory(String category);

}

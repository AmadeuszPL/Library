package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.book.Author;
import com.amadeusz.library.application.model.book.Book;
import com.amadeusz.library.application.model.book.ISBN;
import com.amadeusz.library.infrastructure.repository.entities.BookEntity;

public class DefaultBookEntityMapper implements BookEntityMapper {

    @Override
    public BookEntity map(Book book) {
        Author author = book.getAuthor();
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthorBirthYear(author.getBirthYear());
        bookEntity.setAuthorName(author.getName());
        bookEntity.setCategory(book.getCategory().name());
        bookEntity.setIsbn(book.getIsbn().getValue());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPublicationYear(book.getPublicationYear());
        return bookEntity;
    }

    @Override
    public Book map(BookEntity bookEntity) {
        return new Book(ISBN.of(bookEntity.getIsbn()),
                bookEntity.getTitle(),
                bookEntity.getPublicationYear(),
                new Author(bookEntity.getAuthorName(),
                        bookEntity.getAuthorBirthYear()),
                Book.SubjectCategory.valueOf(bookEntity.getCategory()));
    }

}

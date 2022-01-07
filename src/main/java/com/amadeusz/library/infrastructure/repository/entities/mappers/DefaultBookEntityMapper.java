package com.amadeusz.library.infrastructure.repository.entities.mappers;

import com.amadeusz.library.application.model.book.Author;
import com.amadeusz.library.application.model.book.Book;
import com.amadeusz.library.application.model.book.ISBN;
import com.amadeusz.library.infrastructure.repository.entities.BookEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DefaultBookEntityMapper {

    public static BookEntity map(Book book) {
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

    public static Book map(BookEntity bookEntity) {
        return new Book(ISBN.of(bookEntity.getIsbn()), bookEntity.getTitle(), bookEntity.getPublicationYear(),
                new Author(bookEntity.getAuthorName(), bookEntity.getAuthorBirthYear()),
                Book.SubjectCategory.valueOf(bookEntity.getCategory()));
    }

}

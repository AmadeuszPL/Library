package com.amadeusz.library.application.service

import com.amadeusz.library.application.exceptions.NoInRepositoryException
import com.amadeusz.library.application.model.book.Author
import com.amadeusz.library.application.model.book.Book
import com.amadeusz.library.application.model.book.ISBN
import com.amadeusz.library.infrastructure.repository.BookJpaRepository
import com.amadeusz.library.infrastructure.repository.entities.BookEntity
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import spock.lang.Specification

class BookServiceUnitTests extends Specification {

    BookService bookService
    BookJpaRepository bookRepository

    void setup() {
        bookRepository = Mock()
        bookService = new BookServiceImpl(bookRepository)
    }

    def "book Service should add book to repository"() {

        given:
        def anne = createAnne()
        def anneEntity = createAnneEntity()

        when:
        def returnValue = bookService.add(anne)

        then:
        1 * bookRepository.saveAndFlush(anneEntity) >> anneEntity
        returnValue == anne
    }


    def "bookService getByISBN should throw exception if not in repository"() {

        when:
        bookService.getByISBN('9780...075492047')

        then:
        1 * bookRepository.findById('9780075492047') >> Optional.empty()
        thrown NoInRepositoryException

    }

    def "bookService getByISBN should return book if found in repository"() {

        given:
        def anne = createAnne()
        def anneEntity = createAnneEntity()

        when:
        def result = bookService.getByISBN('9780...075492047')

        then:
        1 * bookRepository.findById('9780075492047') >> Optional.of(anneEntity)
        result == anne
    }

    def "bookService updateBook should update book in repository"() {

        given:
        def anneEntity = createAnneEntity()
        def newAnne = createNewAnne()
        def newAnneEntity = createNewAnneEntity()
        def isbn = newAnneEntity.getIsbn().toString()

        when:
        def result = bookService.updateBook(isbn, newAnne)

        then:
        1 * bookRepository.findById(isbn) >> Optional.of(anneEntity)
        1 * bookRepository.saveAndFlush(newAnneEntity) >> newAnneEntity
        result == newAnne

    }

    def "bookService getAllBooks should return two books"() {

        given:
        def anne = createAnne()
        def anneEntity = createAnneEntity()
        def sampleBook = createSampleBook()
        def sampleBookEntity = createSampleBookEntity()
        def pageable = Pageable.ofSize(2)

        def returnedBooksEntities = new PageImpl(List.of(sampleBookEntity, anneEntity))
        def returnedBooks = new PageImpl(List.of(sampleBook, anne))

        when:
        def result = bookService.getAllBooks(pageable)

        then:
        1 * bookRepository.findAll(pageable) >> returnedBooksEntities
        result == returnedBooks

    }


    private Book createAnne() {
        new Book(ISBN.of('9780075492047'), 'Anne Of Green Gables', 1911,
                new Author('Lucy Maud Montgomery', 1874), Book.SubjectCategory.NOVEL)
    }

    private BookEntity createAnneEntity() {
        def anneEntity = new BookEntity()
        anneEntity.setIsbn('9780075492047')
        anneEntity.setTitle('Anne Of Green Gables')
        anneEntity.setPublicationYear(1911)
        anneEntity.setAuthorName('Lucy Maud Montgomery')
        anneEntity.setAuthorBirthYear(1874)
        anneEntity.setCategory('NOVEL')
        anneEntity
    }

    private Book createNewAnne() {
        new Book(ISBN.of('9780075492047'), 'Anne Of Blue Gables', 1911,
                new Author('Lucy Maud Montgomery', 1874), Book.SubjectCategory.NOVEL)
    }

    private BookEntity createNewAnneEntity() {
        def anneEntity = new BookEntity()
        anneEntity.setIsbn('9780075492047')
        anneEntity.setTitle('Anne Of Blue Gables')
        anneEntity.setPublicationYear(1911)
        anneEntity.setAuthorName('Lucy Maud Montgomery')
        anneEntity.setAuthorBirthYear(1874)
        anneEntity.setCategory('NOVEL')
        anneEntity
    }

    private Book createSampleBook() {
        new Book(ISBN.of('1402894627'), 'Sample Book', 2022,
                new Author('Sample Author', 1992), Book.SubjectCategory.HISTORICAL_FICTION)
    }

    private BookEntity createSampleBookEntity() {
        def sampleBookEntity = new BookEntity()
        sampleBookEntity.setIsbn('1402894627')
        sampleBookEntity.setTitle('Sample Book')
        sampleBookEntity.setPublicationYear(2022)
        sampleBookEntity.setAuthorName('Sample Author')
        sampleBookEntity.setAuthorBirthYear(1992)
        sampleBookEntity.setCategory('HISTORICAL_FICTION')
        sampleBookEntity
    }
}
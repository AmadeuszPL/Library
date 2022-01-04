package com.amadeusz.library.application.service

import com.amadeusz.library.application.exceptions.NoInRepositoryException
import com.amadeusz.library.application.model.book.Author
import com.amadeusz.library.application.model.book.Book
import com.amadeusz.library.application.model.book.ISBN
import com.amadeusz.library.infrastructure.repository.BookJpaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import spock.lang.Specification

@SpringBootTest
class BookServiceTest extends Specification {

    @Autowired
    BookService bookService

    @Autowired
    BookJpaRepository bookRepository

    def setup() {
        bookService.removeAll()
    }

    def anne = new Book(ISBN.of('9780075492047'), 'Anne Of Green Gables', 1911,
            new Author('Lucy Maud Montgomery', 1874), Book.SubjectCategory.NOVEL)

    def 'should return book when add book'() {
        when:
        def book = bookService.add(anne)

        then:
        book == anne
    }

    def 'should get all added books'() {
        given:
        Book anne2 = new Book(ISBN.of('0-19-853453-1'), 'Anne Of Green Gables', 1911,
                new Author('Lucy Maud Montgomery', 1874), Book.SubjectCategory.NOVEL)
        Book anne3 = new Book(ISBN.of('978-92-95055-02-5'), 'Anne Of Green Gables', 1911,
                new Author('Lucy Maud Montgomery', 1874), Book.SubjectCategory.NOVEL)
        def pagedata = PageRequest.of(0,5)

        when:
        bookService.add(anne)
        bookService.add(anne2)
        bookService.add(anne3)

        then:
        bookService.getAllBooks(pagedata).getTotalElements() == 3
        bookService.getAllBooks(pagedata).getContent().size() == 3
    }

    def 'should find two books by title'() {
        given:
        def anne2 = new Book(ISBN.of('0-19-853453-1'), 'Anne Of Green Gables', 1911,
                new Author('Lucy Maud Montgomery', 1874), Book.SubjectCategory.NOVEL)
        def pagedata = PageRequest.of(0,5)

        when:
        bookService.add(anne)
        bookService.add(anne2)

        then:
        bookService.searchByTitle('Anne Of Green Gables', pagedata).getContent().size() == 2
    }

    def 'should find one book by publicationYear '() {
        given:
        def pagedata = PageRequest.of(0,5)

        when:
        bookService.add(anne)

        then:
        bookService.searchByYear(1911, pagedata).getContent().size() == 1
    }

    def 'should find one book by category '() {
        given:
        def pagedata = PageRequest.of(0,5)

        when:
        bookService.add(anne)

        then:
        bookService.searchByCategory('NOVEL', pagedata).getContent().size() == 1
    }

    def 'should find one book by author name'() {
        given:
        def pagedata = PageRequest.of(0,5)

        when:
        bookService.add(anne)

        then:
        bookService.searchByAuthorName('Lucy Maud Montgomery', pagedata).getContent().size() == 1
    }

    def 'should not update because wrong ISBN of book was given'() {
        when:
        bookService.updateBook('0-19-853453-1', anne)

        then:
        thrown(NoInRepositoryException)
    }

    def 'should update book'() {
        when:
        bookService.add(anne)
        anne.setTitle('Anne from Blue Gables')
        bookService.updateBook('9780075492047', anne)

        then:
        bookService.getByISBN('9780075492047').getTitle() == 'Anne from Blue Gables'
    }

    def 'should remove book from repository'() {
        when:
        bookService.add(anne)
        bookService.removeByISBN('9780075492047')
        bookService.getByISBN('9780075492047')

        then:
        thrown(NoInRepositoryException)
    }



}
package com.amadeusz.library.application.service

import com.amadeusz.library.application.model.book.Book
import com.amadeusz.library.application.model.bookitem.BookItem
import com.amadeusz.library.infrastructure.repository.BookItemJpaRepository
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class BookItemServiceSpecification extends Specification {

    @Autowired
    BookItemService bookItemService

    @SpringBean
    BookService bookService = Mock()

    def setup() {
        bookItemService.removeAll()
    }

    BookItem test = new BookItem('12345', '123456')

    def 'should return bookItem when add bookItem'() {
        when:
        def bookItem = bookItemService.add(test)

        then:
        bookItem == test
    }

}
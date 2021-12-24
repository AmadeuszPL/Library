package com.amadeusz.library.infrastructure.controller

import com.amadeusz.library.application.service.BookService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest extends Specification {

    @Autowired
    protected MockMvc mvc

    @SpringBean
    BookService bookService = Mock()

    def "when correct from controller view requests are asked should run methods on controller"() {

        when:
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/books"))
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/books?year=2020"))
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/books?author=Adam Mickiewicz"))
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/books?category=NOVEL"))
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/books?title=Pan Tadeusz"))


        then:
        MockMvcResultMatchers.status().isOk()
        1 * bookService.getAllBooks(_)
        1 * bookService.searchByYear(2020, _)
        1 * bookService.searchByAuthorName("Adam Mickiewicz", _)
        1 * bookService.searchByCategory("NOVEL", _)
        1 * bookService.searchByTitle("Pan Tadeusz", _)

    }


}

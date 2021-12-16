package com.amadeusz.library.infrastructure.controller

import com.amadeusz.library.infrastructure.service.BookService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest extends Specification{

    @Autowired
    protected MockMvc mvc

    //how to mock bookSerbice in bookcontroller
    //BookService bookService =

    def "when get is performed then the response has status 200"() {
        expect: "Status is 200"
        mvc.perform(MockMvcRequestBuilders.get("/api/v1/books"))
        .andExpect(MockMvcResultMatchers.status().isOk())

    }
}

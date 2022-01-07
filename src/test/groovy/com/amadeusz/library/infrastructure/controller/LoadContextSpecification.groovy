package com.amadeusz.library.infrastructure.controller


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextSpecification extends Specification {

    @Autowired (required = false)
    private BookController bookController

    def "when context is loaded then all expected beans are created"() {
        expect: "the BookController is created"
        bookController
    }

}

package com.amadeusz.library.infrastructure.controller;

import com.amadeusz.library.infrastructure.model.BookEntity;
import com.amadeusz.library.infrastructure.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookEntity> list(){
        return bookService.getAllBooks();
    }

    @PostMapping
    public @ResponseBody BookEntity add(@Valid @ModelAttribute("bookEntity") BookEntity book) {
        return bookService.add(book);
    }

}

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
    public List<BookEntity> list() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public @ResponseBody
    BookEntity addOrUpdate(@RequestBody final BookEntity book) {
        return bookService.add(book);
    }

    @GetMapping
    @RequestMapping("/searchByYear/{year}")
    public List<BookEntity> searchByYear(@PathVariable int year) {
        return bookService.searchByYear(year);
    }

    @GetMapping
    @RequestMapping("/searchByAuthorName/{author}")
    public List<BookEntity> searchByAuthorName(@PathVariable String author) {
        return bookService.searchByAuthorName(author);
    }

    @GetMapping
    @RequestMapping("/searchByCategory/{category}")
    public List<BookEntity> searchByCategory(@PathVariable String category) {
        return bookService.searchByCategory(category);
    }

    @GetMapping
    @RequestMapping("/searchByTitle/{title}")
    public List<BookEntity> searchByTitle(@PathVariable String title) {
        return bookService.searchByTitle(title);
    }

    @GetMapping
    @RequestMapping("/{Isbn}")
    public BookEntity getByIsbn(@PathVariable String Isbn) {
        return bookService.getByISBN(Isbn);
    }

    @PostMapping
    @RequestMapping("/updateBookTitle/")
    public @ResponseBody
    BookEntity updateBookTitle(@RequestBody String title, String isbn) {
        return bookService.updateBookTitle(title, isbn);
    }

}

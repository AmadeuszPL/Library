package com.amadeusz.library.infrastructure.controller;

import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.infrastructure.model.BookEntity;
import com.amadeusz.library.infrastructure.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addOrUpdate(@RequestBody final Book book) {
        return book;
    }

    @GetMapping
    public List<BookEntity> search(@RequestParam(required = false, value = "year") Integer year,
                                   @RequestParam(required = false, value = "author") String author,
                                   @RequestParam(required = false, value = "category") String category,
                                   @RequestParam(required = false, value = "title") String title) {
        if (year != null) {
            return bookService.searchByYear(year);
        } else if (author != null) {
            return bookService.searchByAuthorName(author);
        } else if (category != null) {
            return bookService.searchByCategory(category);
        } else if (title != null) {
            return bookService.searchByTitle(title);
        } else {
            return bookService.getAllBooks();
        }
    }

    @GetMapping
    @RequestMapping("/{Isbn}")
    public BookEntity getByIsbn(@PathVariable String Isbn) {
        return bookService.getByISBN(Isbn);
    }

    @PostMapping
    @RequestMapping("/updateBookTitle/")
    public BookEntity updateBookTitle(@RequestBody Map<String, String> json) {
        String title = json.get("title");
        String isbn = json.get("isbn");
        System.out.println(title);
        System.out.println(isbn);
        return bookService.updateBookTitle(title, isbn);
    }

}

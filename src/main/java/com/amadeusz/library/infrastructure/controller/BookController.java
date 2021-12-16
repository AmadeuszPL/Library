package com.amadeusz.library.infrastructure.controller;

import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.infrastructure.model.BookEntity;
import com.amadeusz.library.infrastructure.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book addOrUpdate(@Valid @RequestBody final Book book) {
        return bookService.add(book);
    }

    @GetMapping
    public Page<BookEntity> search(@RequestParam Map<String, String> paramMap,
                                   @PageableDefault(sort = {"publicationYear"},
                                           size = 5) Pageable pageable) {

/*        if (sexcfdduper != null) {
            return bookService.searchByYear(publicationYear, pageable);
        } else if (author != null) {
            return bookService.searchByAuthorName(author, pageable);
        } else if (category != null) {
            return bookService.searchByCategory(category, pageable);
        } else if (title != null) {
            return bookService.searchByTitle(title, pageable);
        } else {*/
            return bookService.getAllBooks(pageable);
/*        }*/
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

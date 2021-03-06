package com.amadeusz.library.infrastructure.controller;

import com.amadeusz.library.application.model.book.Book;
import com.amadeusz.library.application.exceptions.IllegalRequestException;
import com.amadeusz.library.application.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("api/v1/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody final Book book) {
        return bookService.add(book);
    }

    @GetMapping
    public Page<Book> searchBooks(@RequestParam Map<String, String> paramMap,
                                  @PageableDefault(sort = {"publicationYear"},
                                     size = 5) Pageable pageable) {

        if (paramMap.size() > 3) {
            throw new IllegalRequestException("You can pass only one parameter + Pageable");
        } else if (paramMap.isEmpty()) {
            return bookService.getAllBooks(pageable);
        } else {
            Map.Entry<String, String> entry = paramMap.entrySet().iterator().next();
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
                case ("year"):
                    return bookService.searchByYear(Integer.parseInt(value), pageable);
                case ("author"):
                    return bookService.searchByAuthorName(value, pageable);
                case ("category"):
                    return bookService.searchByCategory(value, pageable);
                case ("title"):
                    return bookService.searchByTitle(value, pageable);
                default:
                    throw new IllegalRequestException("Cannot search by this parameter");
            }
        }
    }

    @GetMapping("/{Isbn}")
    public Book getBookByIsbn(@PathVariable String Isbn) {
        return bookService.getByISBN(Isbn);
    }

    @DeleteMapping("/{Isbn}")
    public void removeBookByIsbn(@PathVariable String Isbn) {
        bookService.removeByISBN(Isbn);
    }

    @PatchMapping("/{Isbn}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,
                                           @PathVariable String Isbn) {
        Book book1 = bookService.updateBook(Isbn, book);
        return ResponseEntity.ok(book1);
    }

}

package com.amadeusz.library.infrastructure.controller;

import com.amadeusz.library.application.model.bookitem.BookItem;
import com.amadeusz.library.application.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/bookitems")
public class BookItemController {

    @Autowired
    private BookItemService bookItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookItem addBookItem(@Valid @RequestBody final BookItem bookItem) {
        return bookItemService.add(bookItem);
    }

    @GetMapping
    public Page<BookItem> getBookItemsByIsbn(@RequestParam String bookIsbn, @PageableDefault(sort = {"bookItemStatus"},
            size = 5) Pageable pageable) {
        return bookItemService.getByIsbn(bookIsbn, pageable);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public BookItem getBookItemById(@PathVariable UUID id) {
        return bookItemService.getById(id);
    }

    @DeleteMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void removeById(@PathVariable UUID id) {
        bookItemService.removeById(id);
    }

    @PatchMapping
    @RequestMapping(method = RequestMethod.PATCH)
    public ResponseEntity<BookItem> updateBookItem(@RequestBody BookItem bookItem) {
        BookItem book1 = bookItemService.updateBook(bookItem);
        return ResponseEntity.ok(book1);
    }

}

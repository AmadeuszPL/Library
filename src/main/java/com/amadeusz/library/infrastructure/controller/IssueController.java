package com.amadeusz.library.infrastructure.controller;

import com.amadeusz.library.application.model.bookissue.BookLending;
import com.amadeusz.library.application.model.bookissue.BookReservation;
import com.amadeusz.library.application.model.dto.IssueDTO;
import com.amadeusz.library.application.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/lendBook")
    public BookLending lendBook(@Valid @RequestBody final IssueDTO issueDTO) {
        return issueService.lendBook(issueDTO.getBookIsbn(), issueDTO.getIssuerId());
    }

    @PostMapping("/reserveBook")
    public BookReservation reserveBook(@Valid @RequestBody final IssueDTO issueDTO) {
        return issueService.reserveBook(issueDTO.getBookIsbn(), issueDTO.getIssuerId());
    }

    @PostMapping("/returnBook")
    public BookLending returnBook(@RequestParam UUID bookItemId) {
        return issueService.returnBook(bookItemId);
    }

    @PostMapping("/cancelReservation")
    public BookReservation cancelReservation(@Valid @RequestBody final IssueDTO issueDTO) {
        return issueService.cancelReservation(issueDTO.getBookIsbn(), issueDTO.getIssuerId());
    }

}

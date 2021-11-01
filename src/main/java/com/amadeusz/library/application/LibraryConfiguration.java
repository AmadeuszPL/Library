package com.amadeusz.library.application;

public class LibraryConfiguration {

    private final BookRepository bookRepository;
    private final BookIssueRepository bookIssueRepository;
    private final AccountsRepository accountsRepository;
    private final IssueService issueService;
    private final BookRepositoryService bookService;

    public LibraryConfiguration(BookRepository bookRepository,
                                BookIssueRepository bookIssueRepository,
                                AccountsRepository accountsRepository) {
        this.bookRepository = bookRepository;
        this.bookIssueRepository = bookIssueRepository;
        this.accountsRepository = accountsRepository;
        this.issueService = new IssueService(bookIssueRepository);
        this.bookService = new BookRepositoryService(bookRepository);
    }

    public IssueService getIssueService() {
        return issueService;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public BookIssueRepository getBookIssueRepository() {
        return bookIssueRepository;
    }

    public AccountsRepository getAccountsRepository() {
        return accountsRepository;
    }

    public BookRepositoryService getBookService() {
        return bookService;
    }
}

package com.amadeusz.library.application;

public class LibraryConfiguration {

    private final BookRepository bookRepository;
    private final BookItemRepository bookItemRepository;
    private final BookIssueRepository bookIssueRepository;
    private final AccountsRepository accountsRepository;
    private final IssueService issueService;
    private final BookService bookService;

    public LibraryConfiguration(BookRepository bookRepository,
                                BookItemRepository bookItemRepository,
                                BookIssueRepository bookIssueRepository,
                                AccountsRepository accountsRepository) {
        this.bookRepository = bookRepository;
        this.bookItemRepository = bookItemRepository;
        this.bookIssueRepository = bookIssueRepository;
        this.accountsRepository = accountsRepository;

        this.issueService = new IssueService(bookIssueRepository);
        this.bookService = new BookService(bookRepository);
        //this.bookService = new BookItemService(bookItemRepositoryRepository);
    }

    public IssueService getIssueService() {
        return issueService;
    }

/*   public BookItemRepository getBookRepository() {
        return bookRepository;
    }*/

    public BookIssueRepository getBookIssueRepository() {
        return bookIssueRepository;
    }

    public AccountsRepository getAccountsRepository() {
        return accountsRepository;
    }

    public BookService getBookService() {
        return bookService;
    }
}

import com.amadeusz.library.application.LibraryConfiguration;
import com.amadeusz.library.application.accounts.AccountsService;
import com.amadeusz.library.application.accounts.DefaultAccountsService;
import com.amadeusz.library.application.accounts.librarymembers.LibraryMembersRepository;
import com.amadeusz.library.application.book.BookRepository;
import com.amadeusz.library.application.book.BookService;
import com.amadeusz.library.application.book.DefaultBookService;
import com.amadeusz.library.application.bookissue.BookIssueRepository;
import com.amadeusz.library.application.bookissue.DefaultIssueService;
import com.amadeusz.library.application.bookissue.IssueService;
import com.amadeusz.library.application.bookitem.BookItemRepository;
import com.amadeusz.library.application.bookitem.BookItemService;
import com.amadeusz.library.application.bookitem.DefaultBookItemService;
import com.amadeusz.library.infrastructure.accounts.librarymembers.InMemoryLibraryMembersRepository;
import com.amadeusz.library.infrastructure.book.InMemoryBookRepository;
import com.amadeusz.library.infrastructure.bookissue.InMemoryBookIssueRepository;
import com.amadeusz.library.infrastructure.bookitem.InMemoryBookItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(name = "getLibraryConfiguration")
    public LibraryConfiguration getLibraryConfiguration(){
        return new LibraryConfiguration(getBookService(), getBookItemService(),
                getIssueService(), getAccountsService());
    }

    @Bean(name = "getAccountsService")
    public AccountsService getAccountsService() {
        return new DefaultAccountsService(getLibraryMembersRepository());
    }

    @Bean(name = "issueService")
    public IssueService getIssueService() {
        return new DefaultIssueService(getBookIssueRepository(),
                getBookItemRepository(), getLibraryMembersRepository());
    }

    @Bean(name = "bookService")
    public BookService getBookService() {
        return new DefaultBookService(getBookRepository());
    }

    @Bean(name = "bookItemService")
    public BookItemService getBookItemService() {
        return new DefaultBookItemService(getBookItemRepository(), getBookRepository());
    }

    @Bean(name = "libraryMemberRepository")
    public LibraryMembersRepository getLibraryMembersRepository() {
        return new InMemoryLibraryMembersRepository();
    }

    @Bean(name = "bookIssueRepository")
    public BookIssueRepository getBookIssueRepository() {
        return new InMemoryBookIssueRepository();
    }

    @Bean(name = "bookItemRepository")
    public BookItemRepository getBookItemRepository() {
        return new InMemoryBookItemRepository();
    }

    @Bean(name = "bookRepository")
    public BookRepository getBookRepository() {
        return new InMemoryBookRepository();
    }

}

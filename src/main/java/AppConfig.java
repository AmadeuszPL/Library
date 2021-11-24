import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.amadeusz.library")
public class AppConfig {

//    @Bean(name = "getLibraryConfiguration")
//    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
//    //unique per request
////    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
//    public LibraryConfiguration getLibraryConfiguration() {
//        return new LibraryConfiguration(getBookService(), getBookItemService(),
//                getIssueService(), getAccountsService());
//    }
//
//    @Bean(name = "getAccountsService")
//    public AccountsService getAccountsService() {
//        return new DefaultAccountsService(getLibraryMembersRepository());
//    }
//
//    @Bean(name = "issueService")
//    public IssueService getIssueService() {
//        return new DefaultIssueService(getBookIssueRepository(),
//                getBookItemRepository(), getLibraryMembersRepository());
//    }
//
//    @Bean(name = "bookService")
//    public BookService getBookService() {
//        return new DefaultBookService(getBookRepository());
//    }
//
//    @Bean(name = "bookItemService")
//    public BookItemService getBookItemService() {
//        return new DefaultBookItemService(getBookItemRepository(), getBookRepository());
//    }
//
//    @Bean(name = "libraryMemberRepository")
//    public LibraryMembersRepository getLibraryMembersRepository() {
//        return new InMemoryLibraryMembersRepository();
//    }
//
//    @Bean(name = "bookIssueRepository")
//    public BookIssueRepository getBookIssueRepository() {
//        return new InMemoryBookIssueRepository();
//    }
//
//    @Bean(name = "bookItemRepository")
//    public BookItemRepository getBookItemRepository() {
//        return new InMemoryBookItemRepository();
//    }
//
//    @Bean(name = "bookRepository")
//    public BookRepository getBookRepository() {
//        return new InMemoryBookRepository();
//    }

}

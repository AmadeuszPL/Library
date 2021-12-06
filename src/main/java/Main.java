import com.amadeusz.library.application.*;
import com.amadeusz.library.application.accounts.Address;
import com.amadeusz.library.application.accounts.Person;
import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.infrastructure.service.AccountsService;
import com.amadeusz.library.application.book.Author;
import com.amadeusz.library.application.book.Book;
import com.amadeusz.library.infrastructure.service.BookService;
import com.amadeusz.library.application.book.ISBN;
import com.amadeusz.library.infrastructure.service.IssueService;
import com.amadeusz.library.application.bookitem.BookItem;
import com.amadeusz.library.infrastructure.service.BookItemService;
import com.amadeusz.library.application.bookitem.RackNumber;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static com.amadeusz.library.application.book.Book.SubjectCategory.*;

public class Main {

    public static void main(String[] args) throws Exception {

/*        LibraryConfiguration singleTonTest = appContext.getBean("libraryConfiguration",
                LibraryConfiguration.class);

        System.out.println(singleTonTest);

        LibraryConfiguration config = appContext.getBean("libraryConfiguration",
                LibraryConfiguration.class);

        System.out.println(config);*/

//        LibraryConfiguration config =
//                new LibraryConfiguration(new InMemoryBookRepository(),
//                        new InMemoryBookItemRepository(),
//                        new InMemoryBookIssueRepository(),
//                        new InMemoryLibraryMembersRepository());

        Author adamMickiewicz = new Author("Adam Mickiewicz", 1798);
        Author henrykSienkiewicz = new Author("Henryk Sienkiewicz", 1846);
        Author olgaTokarczuk = new Author("Olga Tokarczuk", 1962);
        Author agathaChristie = new Author("Agatha Christie", 1890);

        ISBN panTadeuszISBN = ISBN.of("1909669512");
        ISBN dziadyISBN = ISBN.of("8385443509");
        ISBN quoVadisISBN = ISBN.of("1934169064");
        ISBN bieguniISBN = ISBN.of("830805594X");
        ISBN andThenThereWereNoneISBN = ISBN.of("0007136838");
        ISBN murderATTheVicarageISBN = ISBN.of("1579126251");

        Book panTadeusz = new Book(panTadeuszISBN, "Pan " +
                "Tadeusz", 2013, adamMickiewicz, EPIC_POETRY);
        Book dziady = new Book(dziadyISBN, "Dziady Czesc III", 1995,
                adamMickiewicz, EPIC_POETRY);
        Book quoVadis = new Book(quoVadisISBN, "Quo Vadis", 2006,
                henrykSienkiewicz, HISTORICAL_FICTION);
        Book bieguni = new Book(bieguniISBN, "Bieguni", 2018, olgaTokarczuk,
                LITERALLY_FICTION);
        Book andThenThereWereNone = new Book(andThenThereWereNoneISBN, "And " +
                "Then There Were None", 2003, agathaChristie, DETECTIVE);
        Book murderAtTheVicarage = new Book(murderATTheVicarageISBN, "The " +
                "Murder at a Vicarage", 2002, agathaChristie, DETECTIVE);

        Address mickiewicz12 = new Address("Mickiewicza 12", "Nysa",
                "48-300", "Poland");
        Address broniewskiego7 = new Address("Broniewskiego 7", "Nysa",
                "48-304", "Poland");

        Person janKowalskiPerson = new Person("Jan Kowalski", mickiewicz12,
                "johan.kowalskie@gmail.com" +
                ".pl", "550423455345652");
        Person piotrNowakPerson = new Person("Piotr Nowak", broniewskiego7,
                "pieter@nowak.com",
                "52352363464577");

        LibraryMember janKowalski = new LibraryMember(
                "janKowalski69",
                "Kowal123",
                janKowalskiPerson);
        LibraryMember piotrNowak = new LibraryMember("piotrNowak55",
                "Nowak123", piotrNowakPerson);


/*        BookService bookService = appContext.getBean("bookService", BookService.class);
        BookItemService bookItemService = config.getBookItemService();
        IssueService issueService = appContext.getBean("issueService", IssueService.class);
        AccountsService libraryMemberService = config.getLibraryMemberService();*/

/*        libraryMemberService.addLibraryMember(janKowalski);
        libraryMemberService.addLibraryMember(piotrNowak);

        bookService.add(panTadeusz);
        bookService.add(dziady);
        bookService.add(bieguni);
        bookService.add(quoVadis);
        bookService.add(andThenThereWereNone);
        bookService.add(murderAtTheVicarage);

        BookItem panTadeuszBookItem = new BookItem(panTadeusz,
                new RackNumber("554321"));
        bookItemService.addBookItem(panTadeuszBookItem);
        bookItemService.addBookItem(new BookItem(dziady,
                new RackNumber("776543")));
        bookItemService.addBookItem(new BookItem(dziady,
                new RackNumber("776544")));
        bookItemService.addBookItem(new BookItem(dziady,
                new RackNumber("776545")));
        bookItemService.addBookItem(new BookItem(bieguni,
                new RackNumber("123344")));
        bookItemService.addBookItem(new BookItem(quoVadis, new RackNumber(
                "554332")));
        bookItemService.addBookItem(new BookItem(andThenThereWereNone, new RackNumber(
                "112334")));
        bookItemService.addBookItem(new BookItem(murderAtTheVicarage,
                new RackNumber("443211")));

        System.out.println(bookService);
        System.out.println();

        System.out.println("Update 'Pan Tadeusz' title");
        bookService.updateBookTitle("Pan Tadeusz (Polish Language Version)",
                panTadeuszISBN.getValue());
        System.out.println();

        System.out.println(bookService);
        System.out.println();

        System.out.println("Search book by category 'DETECTIVE':");
        List<Book> booksByCategory =
                bookService.searchByCategory(DETECTIVE.name());
        System.out.println(booksByCategory);
        System.out.println();

        System.out.println("Search book by title 'Quo Vadis':");
        List<Book> quo_vadis = bookService.searchByTitle("Quo Vadis");
        System.out.println(quo_vadis);
        System.out.println();

        System.out.println("Search book by author name 'Olga Tokarczuk':");
        List<Book> olga_tokarczuk = bookService.searchByAuthorName("Olga Tokarczuk");
        System.out.println(olga_tokarczuk);
        System.out.println();

        System.out.println("Search book by publication year '2002':");
        List<Book> booksByYear = bookService.searchByYear(2002);
        System.out.println(booksByYear);
        System.out.println();

        System.out.println("Print Book Item Service:");
        System.out.println(bookItemService);


        issueService.lendBook(dziadyISBN.getValue(), janKowalski.getId());
        issueService.lendBook(bieguniISBN.getValue(), janKowalski.getId());
        issueService.lendBook(quoVadisISBN.getValue(), janKowalski.getId());
        issueService.lendBook(andThenThereWereNoneISBN.getValue(), janKowalski.getId());
        issueService.lendBook(murderATTheVicarageISBN.getValue(), piotrNowak.getId());

        issueService.lendBook(panTadeuszISBN.getValue(), janKowalski.getId());
        issueService.reserveBook(panTadeuszISBN.getValue(), piotrNowak.getId());
        issueService.returnBook(panTadeuszBookItem.getId());
        issueService.cancelReservation(panTadeuszISBN.getValue(), piotrNowak.getId());
        issueService.lendBook(panTadeuszISBN.getValue(), janKowalski.getId());

        InDataBaseBookRepository bookRepository = new InDataBaseBookRepository();
        bookRepository.create(panTadeusz);*/

    }
}

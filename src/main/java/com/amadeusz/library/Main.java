package com.amadeusz.library;

import com.amadeusz.library.application.*;
import com.amadeusz.library.infrastructure.InMemoryAccountsRepository;
import com.amadeusz.library.infrastructure.InMemoryBookIssueRepository;
import com.amadeusz.library.infrastructure.InMemoryBookItemRepository;
import com.amadeusz.library.infrastructure.InMemoryBookRepository;

public class Main {

    public static void main(String[] args) {

        LibraryConfiguration config =
                new LibraryConfiguration(new InMemoryBookRepository(),
                        new InMemoryBookItemRepository(),
                        new InMemoryBookIssueRepository(),
                        new InMemoryAccountsRepository());

/*        IssueService issueService = config.getIssueService();

        BookItemService bookService = config.getBookService();

        Address mickiewicz12 = new Address("Mickiewicza 12", "Nysa",
                "48-300", "Poland");
        Address broniewskiego7 = new Address("Broniewskiego 7", "Nysa",
                "48-304", "Poland");

        Person janKowalski = new Person("Jan Kowalski", mickiewicz12, "jk@o2" +
                ".pl", "550423455345652");
        Person piotrNowak = new Person("Piotr Nowak", broniewskiego7, "p@m",
                "52352363464577");

        Account bibliotekarz = new Librarian("janKowalski69", "Kowal123",
                janKowalski);
        LibraryMember bibliotekarzJakoCzlonek = new LibraryMember("janKowalski69",
                "Kowal123",
                janKowalski);
        LibraryMember czlonek = new LibraryMember("piotrNowak55",
                "Nowak123", piotrNowak);

        AccountsRepository accountsRepository =
                new InMemoryAccountsRepository();

        accountsRepository.addUserAccount(czlonek);
        accountsRepository.addUserAccount(bibliotekarz);
        accountsRepository.addUserAccount(bibliotekarzJakoCzlonek);

        System.out.println("-----PRINT CONTENT OF ACCOUNTS REPOSITORY------");
        System.out.println(accountsRepository);
        System.out.println();

        Author adamMickiewicz = new Author("Adam Mickiewicz", 1765);
        Author henrykSienkiewicz = new Author("Henryk Sienkiewicz", 1821);
        Author olgaTokarczuk = new Author("Olga Tokarczuk", 1978);

        Book panTadeusz = new Book(ISBN.of("0123456789"), "Pan " +
                "Tadeusz", 1923, adamMickiewicz, Book.SubjectCategory.HISTORICAL_FICTION);
        Book wPustyni = new Book(sampleISBN, "W pustyni i w puszczy", 1843,
                henrykSienkiewicz, Book.SubjectCategory.LITERALLY_FICTION);
        Book bieguni = new Book(sampleISBN2, "Bieguni", 2007, olgaTokarczuk,
                Book.SubjectCategory.MYSTERY);

        BookItem panTadeuszBookItem = new BookItem(panTadeusz,
                new RackNumber("123456"));
        BookItem panTadeusz2 = new BookItem(panTadeusz, new RackNumber(
                "883345"));
        BookItem wPustyniBookItem = new BookItem(wPustyni,
                new RackNumber("456789"));
        BookItem wPustyni2 = new BookItem(wPustyni, new RackNumber("884433"));
        BookItem bieguniBookItem = new BookItem(bieguni, new RackNumber(
                "445523"));
        BookItem bieguniBookItem2 = new BookItem(bieguni, new RackNumber(
                "445593"));

        bookService.addBook(panTadeuszBookItem);
        bookService.addBook(panTadeusz2);
        bookService.addBook(wPustyniBookItem);
        bookService.addBook(wPustyni2);
        bookService.addBook(bieguniBookItem);
        bookService.addBook(bieguniBookItem2);

        System.out.println("-------- PRINT BOOK REPOSITORY -------");
        System.out.println(bookService);
        System.out.println();

        System.out.println("-------- SEARCH BY TITLE -------");
        System.out.println(bookService.searchByTitle("Pan Tadeusz"));
        System.out.println();

        System.out.println("-------- SEARCH BY CATEGORY -------");
        System.out.println(bookService.searchByCategory(Book.SubjectCategory.HISTORICAL_FICTION));
        System.out.println();

        System.out.println("-------- SEARCH BY YEAR -------");
        System.out.println(bookService.searchByYear(1923));
        System.out.println();

        System.out.println("-------- SEARCH BY AUTHOR -------");
        System.out.println(bookService.searchByAuthor("Adam Mickiewicz"));
        System.out.println();

        bibliotekarzJakoCzlonek.addCreditCard(5555555555554444L);


        issueService.reserveBook(panTadeusz2.getId(), bibliotekarzJakoCzlonek);
        issueService.cancelReservation(panTadeusz2.getId(), bibliotekarzJakoCzlonek);
        issueService.lendBook(panTadeusz2.getId(), czlonek);
        issueService.reserveBook(panTadeusz2.getId(), bibliotekarzJakoCzlonek);
        issueService.returnBook(panTadeusz2.getId());
        issueService.reserveBook(bieguniBookItem.getId(), czlonek);
        issueService.reserveBook(bieguniBookItem.getId(), bibliotekarzJakoCzlonek);
        issueService.cancelReservation(bieguniBookItem.getId(), czlonek);
        issueService.lendBook(bieguniBookItem.getId(), bibliotekarzJakoCzlonek);


        System.out.println();
        System.out.println("Print Issue Service");
        System.out.println(issueService);*/



        Author adamMickiewicz = new Author("Adam Mickiewicz", 1765);
        Author henrykSienkiewicz = new Author("Henryk Sienkiewicz", 1821);
        Author olgaTokarczuk = new Author("Olga Tokarczuk", 1978);

        ISBN sampleISBN = ISBN.of("97 8 0 3 0 6 4 -0-6-----157");
        ISBN sampleISBN2 = ISBN.of("978-3-16-1484----10-0");

        Book panTadeusz = new Book(ISBN.of("0123456789"), "Pan " +
                "Tadeusz", 1923, adamMickiewicz, Book.SubjectCategory.HISTORICAL_FICTION);
        Book wPustyni = new Book(sampleISBN, "W pustyni i w puszczy", 1843,
                henrykSienkiewicz, Book.SubjectCategory.LITERALLY_FICTION);
        Book bieguni = new Book(sampleISBN2, "Bieguni", 2007, olgaTokarczuk,
                Book.SubjectCategory.MYSTERY);


        BookService bookService = config.getBookService();



        bookService.add(panTadeusz);
        bookService.add(wPustyni);
        bookService.add(bieguni);
        bookService.updateBookData(new Book(ISBN.of("0123456789"), "Pan " +
                "Tadeusz i Templariusze", 1923, adamMickiewicz,
                Book.SubjectCategory.HISTORICAL_FICTION));
        bookService.getByISBN("0123456789");


        System.out.println(bookService);

        System.out.println(bookService.getByISBN("0123456789"));

    }
}

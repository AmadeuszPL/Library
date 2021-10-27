package com.amadeusz.libraryfun;

import java.util.UUID;

public class Main {

    public static void main(String[] args) throws Exception {

        Adress mickiewicz12 = new Adress("Mickiewicza 12", "Nysa",
                "48-300", "Poland");
        Adress broniewskiego7 = new Adress("Broniewskiego 7", "Nysa",
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
//        Author galAnonim = new Author("Gal Anonim", 980);

        ISBN sampleISBN = ISBN.of("97 8 0 3 0 6 4 -0-6-----157");
        ISBN sampleISBN2 = ISBN.of("978-3-16-1484----10-0");

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


        BookRepository bookRepository = new InMemoryBookRepository();

        bookRepository.addBook(panTadeuszBookItem);
        bookRepository.addBook(panTadeusz2);
        bookRepository.addBook(wPustyniBookItem);
        bookRepository.addBook(wPustyni2);
        bookRepository.addBook(bieguniBookItem);
        bookRepository.addBook(bieguniBookItem2);
        bookRepository.deleteBookUsingId(panTadeusz2.getId());

        System.out.println("-------- PRINT BOOK REPOSITORY -------");
        System.out.println(bookRepository);
        System.out.println();

        System.out.println("-------- SEARCH BY TITLE -------");
        System.out.println(bookRepository.searchByTitle("Pan Tadeusz").values());
        System.out.println();

        System.out.println("-------- SEARCH BY CATEGORY -------");
        System.out.println(bookRepository.searchByCategory(Book.SubjectCategory.HISTORICAL_FICTION).values());
        System.out.println();

        System.out.println("-------- SEARCH BY YEAR -------");
        System.out.println(bookRepository.searchByYear(1923).values());
        System.out.println();

        System.out.println("-------- SEARCH BY AUTHOR -------");
        System.out.println(bookRepository.searchByAuthor("Adam Mickiewicz").values());
        System.out.println();

        BookIssueRepository issueRepository = new InMemoryBookIssueRepository();

        System.out.println("-------RESERVING ALL BOOKS NAMED Herr " +
                "Tadeusz--------");
        for (BookItem bookItem :
                bookRepository.searchByTitle("Pan Tadeusz").values()) {
            System.out.println("Reserving: " + bookItem);
            issueRepository.addIssue(new BookIssue(bookItem.getId(),
                    BookIssue.BookStatus.RESERVED), czlonek);
        }
        System.out.println();

        System.out.println("----- EXTENDING RESERVATION TIME -----");
        System.out.println("Reserving: " + panTadeuszBookItem);
        issueRepository.addIssue(new BookIssue(panTadeuszBookItem.getId(),
                BookIssue.BookStatus.RESERVED), czlonek);
        System.out.println();

        System.out.println("-----TRYING TO LOAN ALL THE BOOKS WITH TITLE " +
                "CONTAINING i-----");
        for (BookItem bookItem : bookRepository.searchByTitle("i").values()) {
            System.out.println("Loaning: " + bookItem);
            System.out.println();
            issueRepository.addIssue(new BookIssue(bookItem.getId(),
                    BookIssue.BookStatus.LOANED), czlonek);
        }

        bookRepository.addBook(panTadeusz2);
        issueRepository.addIssue(new BookIssue(panTadeusz2.getId(),
                BookIssue.BookStatus.LOANED), bibliotekarzJakoCzlonek);

        System.out.println();
        System.out.println("--------PRINT WHOLE ISSUE REPOSITORY-------");
        System.out.println(issueRepository);
        System.out.println();


        System.out.println("----- RENTING Pan Tadeusz -----");
        System.out.println("Reserving: " + panTadeuszBookItem);
        issueRepository.addIssue(new BookIssue(panTadeuszBookItem.getId(),
                BookIssue.BookStatus.LOANED), czlonek);

        System.out.println("----- RENTING Pan Tadeusz (different user) -----");
        System.out.println("Reserving: " + panTadeuszBookItem);
        issueRepository.addIssue(new BookIssue(panTadeuszBookItem.getId(),
                BookIssue.BookStatus.LOANED), bibliotekarzJakoCzlonek);
        System.out.println();

        System.out.println("---------- Check who took book by Book ID " +
                "--------");
        UUID searchedUser =
                issueRepository.whoTookBookID(panTadeuszBookItem.getId());

        System.out.println(accountsRepository.searchUserById(searchedUser));
        System.out.println();

        System.out.println("---------- Check books took by user ----------");
        for (UUID uuid : issueRepository.booksRentedByUser(czlonek)) {
            System.out.println(bookRepository.searchById(uuid));
        }

        issueRepository.returnBook(panTadeusz2.getId(), bibliotekarzJakoCzlonek);
        System.out.println(issueRepository);

        System.out.println("----- TRYING TO RENT BOOK AS DIFFERENT USER -----");
        issueRepository.addIssue(new BookIssue(panTadeusz2.getId(),
                BookIssue.BookStatus.LOANED), bibliotekarzJakoCzlonek);

        System.out.println(issueRepository);
    }

}

package com.amadeusz.libraryfun;

public class Main {

    public static void main(String[] args) {

        Adress mickiewicz12 = new Adress("Mickiewicza 12", "Nysa",
                "48-300", "Poland");
        Person janKowalski = new Person("Jan Kowalski", mickiewicz12, "jk@o2" +
                ".pl", "550423455345652");
        Librarian bibliotekarz = new Librarian("janKowalski69", "Kowal123",
                janKowalski);

        Adress broniewskiego4 = new Adress("Broniewskiego 4", "Nysa",
                "48-300", "Poland");
        Person piotrNowak = new Person("Piotr Nowak", broniewskiego4, "p@m",
                "52352363464577");
        LibraryMember uczestnik = new LibraryMember("piotrNowak55",
                "Nowak123", piotrNowak);

        System.out.println(bibliotekarz);
        System.out.println();

        InMemoryBookRepository bookRepository = new InMemoryBookRepository();

        Author adamMickiewicz = new Author("Adam Mickiewicz", 1765);
        Author henrykSienkiewicz = new Author("Henryk Sienkiewicz", 1821);
        Author olgaTokarczuk = new Author("Olga Tokarczuk", 1978);
        Author galAnonim = new Author("Gal Anonim", 980);

        BookItem panTadeusz = new BookItem(010010101000d, "274637283940",
                "Pan Tadeusz",
                1965,
                adamMickiewicz, SubjectCategory.HISTORICAL_FICTION,
                new RackNumber("123345"));

        BookItem panTadeusz2 = new BookItem(540010101000d, "984592736483",
                "Pan Tadeusz",
                1965,
                adamMickiewicz, SubjectCategory.HISTORICAL_FICTION,
                new RackNumber("458832"));

        BookItem wPustyni = new BookItem(660010101000d, "21113455363", "W " +
                "Pustyni i w " +
                "Puszczy", 1877, henrykSienkiewicz,
                SubjectCategory.ADVENTURE, new RackNumber("135531"));

        BookItem bieguni = new BookItem(770010101000d, "765838292466",
                "Bieguni", 2007,
                olgaTokarczuk, SubjectCategory.MYSTERY, new RackNumber(
                "330956"));

        BookItem kronikaPolska = new BookItem(890010101000d, "17758392039",
                "Kroniki Galla " +
                        "Anonima", 1040, galAnonim,
                SubjectCategory.HISTORICAL_FICTION, new RackNumber("110094"));

        BookItem kosciUmarlych = new BookItem(990010101000d, "998877665544",
                "Prowadz swoj " +
                        "plog przez kosci umarlych", 2009, olgaTokarczuk,
                SubjectCategory.LITERALLY_FICTION, new RackNumber("110094"));


        bibliotekarz.addBook(bookRepository, panTadeusz);
        bibliotekarz.addBook(bookRepository, panTadeusz2);
        bibliotekarz.addBook(bookRepository, wPustyni);
        bibliotekarz.addBook(bookRepository, bieguni);
        bibliotekarz.addBook(bookRepository, kronikaPolska);
        bibliotekarz.addBook(bookRepository, kosciUmarlych);

        System.out.println();

        System.out.println("Search by title");

        bookRepository.searchByTitle("Pan Tadeusz");

        System.out.println();
        System.out.println("Search by subject");
        bookRepository.searchByCategory(SubjectCategory.HISTORICAL_FICTION);
        System.out.println();
        System.out.println("Search by Author");
        bookRepository.searchByAuthor("Adam Mickiewicz");
        System.out.println();
        System.out.println("Search by Publication Year");
        bookRepository.searchByYear(1965);
        System.out.println();

        System.out.println(InMemoryBookRepository.countBooks);

        bookRepository.getBooks();

         System.out.println("Renting books with ISBN 984592736483 and " +
                "21113455363 by Piotr Nowak");


        uczestnik.checkOutBookByISBN(bookRepository.getBooks(),010010101000d);
        uczestnik.checkOutBookByISBN(bookRepository.getBooks(),540010101000d);
        uczestnik.checkOutBookByISBN(bookRepository.getBooks(),660010101000d );
        uczestnik.checkOutBookByISBN(bookRepository.getBooks(), 770010101000d);
        uczestnik.checkOutBookByISBN(bookRepository.getBooks(), 890010101000d);
        uczestnik.reserveBookByISBN(bookRepository.getBooks(), 990010101000d);
        uczestnik.checkOutBookByISBN(bookRepository.getBooks(), 990010101000d);
        uczestnik.reserveBookByISBN(bookRepository.getBooks(), 990010101000d);
        uczestnik.checkOutBookByISBN(bookRepository.getBooks(), 990010101000d);

        System.out.println();

        /*System.out.println("Librarian checking who rented book with ISBN " +
                "21113455363");
        Person renter = bibliotekarz.checkWhoIssuedBook(bookRepository.getDb(),
                "21113455363");
        System.out.println(renter);


        System.out.println();
        System.out.println("Print books Issueed by Piotr Nowak");

        bibliotekarz.printUserIssuedBooks(bookRepository.getDb(), uczestnik);



        //System.out.println("Print book Location using Rack Number");
        //panTadeusz.getRackNumber().printBookLocation();
	// write your code here*/
    }

}

package com.amadeusz.libraryfun;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Adress mickiewicz12 = new Adress("Mickiewicza 12","Nysa",
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

        BookItem panTadeusz = new BookItem("274637283940", "Pan Tadeusz",
                1965,
                adamMickiewicz, SubjectCategory.HISTORICAL_FICTION,
                new RackNumber("123345"));

        BookItem panTadeusz2 = new BookItem("984592736483", "Pan Tadeusz",
                1965,
                adamMickiewicz, SubjectCategory.HISTORICAL_FICTION,
                new RackNumber("458832"));

        bibliotekarz.addBook(bookRepository.getDb(), panTadeusz);
        bibliotekarz.addBook(bookRepository.getDb(), panTadeusz2);

        /*
        bookRepository.addBook(panTadeusz);
        bookRepository.addBook(panTadeusz2);*/
        System.out.println("Search by title");
        bookRepository.searchByTitle("adeusz");
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

        System.out.println(panTadeusz.getIssueDate());

        uczestnik.checkOutBookByISBN(bookRepository.getDb(),"984592736483");
        uczestnik.checkOutBookByISBN(bookRepository.getDb(),"984592736483");

        //System.out.println("Print book Location using Rack Number");
        //panTadeusz.getRackNumber().printBookLocation();
	// write your code here*/
    }
}

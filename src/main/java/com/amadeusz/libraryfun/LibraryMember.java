package com.amadeusz.libraryfun;

class LibraryMember extends Account {

    int totalBooksCheckedOut;

    public LibraryMember(String login, String password, Person person) {
        super(login, password, person);
    }

    public int getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    public void addOneBook() {
        totalBooksCheckedOut++;
    }

    public void subtractOneBook() {
        totalBooksCheckedOut--;
    }


    /*   @Override
    public boolean checkOutBookByISBN(Map<Double, BookItem> db,
                                      double barcode) {
        if (bookLimitReached()) {
            System.out.println("You already loaned max amount of books (5).");
            return false;
        }

        BookItem book = db.get(barcode);
        if (book == null) {
            System.out.println("There is no such book in database");
            return false;
        } else {
            LocalDate date = book.getIssueDate();
            if (date == null || (book.getIssuer().equals(this) && book.getBookStatus().equals(BookStatus.RESERVED))) {
                book.setIssueDate();
                book.setDueDateLoan();
                book.setBookStatus(BookStatus.LOANED);
                book.setIssuer(this);
                incrementTotalBooksCheckedOut();
                System.out.println("Successfully loaned : " + book.getTitle());
                return true;
            } else {
                if (book.getIssuer().equals(this)) {
                    System.out.println("You've already loaned this book.");
                } else {
                    System.out.println(book.getBookStatus());
                }
                return false;
            }
        }
    }

    @Override
    public void reserveBookByISBN(Map<Double, BookItem> db, double barcode) {
        BookItem book = db.get(barcode);
        LocalDate date = book.getIssueDate();
        if (date == null) {
            book.setIssueDate();
            book.setDueDateReserve();
            book.setBookStatus(BookStatus.RESERVED);
            book.setIssuer(this);
            System.out.println("Successfully reserved : " + book.getTitle());
        } else {
            if (book.getIssuer().equals(this) && book.getBookStatus().equals(BookStatus.LOANED)) {
                System.out.println("This book is already loaned by you!");
            } else {
                System.out.println("You've already reserved this book.");
            }
        }
    }

    public boolean bookLimitReached() {
        return totalBooksCheckedOut >= ConstantValues.MAX_BOOKS_ISSUED_BY_USER;
    }

    public void incrementTotalBooksCheckedOut(){
        totalBooksCheckedOut++;
    }*/
}

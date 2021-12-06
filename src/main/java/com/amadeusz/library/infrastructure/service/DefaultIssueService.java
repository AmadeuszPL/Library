package com.amadeusz.library.infrastructure.service;

import com.amadeusz.library.application.ConstantValues;
import com.amadeusz.library.application.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.infrastructure.repository.LibraryMembersRepository;
import com.amadeusz.library.infrastructure.repository.BookIssueRepository;
import com.amadeusz.library.application.bookissue.BookLending;
import com.amadeusz.library.application.bookissue.BookReservation;
import com.amadeusz.library.application.bookitem.BookItem;
import com.amadeusz.library.infrastructure.repository.BookItemRepository;
import com.amadeusz.library.application.exceptions.IllegalOperationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static java.time.temporal.ChronoUnit.DAYS;

//@Service("issueService")
public class DefaultIssueService implements IssueService {

    private final BookIssueRepository issueRepository;
    private final BookItemRepository bookItemRepository;
    private final LibraryMembersRepository libraryMembersRepository;

    public DefaultIssueService(BookIssueRepository issueRepository,
                               BookItemRepository bookItemRepository,
                               LibraryMembersRepository libraryMembersRepository) {
        this.issueRepository = issueRepository;
        this.bookItemRepository = bookItemRepository;
        this.libraryMembersRepository = libraryMembersRepository;
    }

    @Override
    public void lendBook(String isbn, UUID issuerId) {
        LibraryMember libraryMember = checkIfUserExistsAndCanLendMoreBooks(issuerId);
        checkIfUserLoanedBookOfThisIsbnBefore(isbn, issuerId);
        if (!checkIfBookIsAvailable(isbn)) {
            throw new IllegalOperationException("Book is not available");
        }
        verifyAndUpdateReservations(isbn, issuerId);
        createNewBookLending(isbn, issuerId);
        addOneBookToLibraryMember(libraryMember);
    }

    @Override
    public void reserveBook(String isbn, UUID issuerId) {
        if (checkIfBookIsAvailable(isbn)) {
            throw new IllegalOperationException("You cannot reserve book that" +
                    " is available to lend");
        }
        checkIfBookReservedByUser(isbn, issuerId);
        addNewReservationToIssueRepository(issuerId, isbn);
    }

    @Override
    public void returnBook(UUID bookItemId) {
        BookItem bookItem = bookItemRepository.readByBookItemId(bookItemId);
        if (bookItem.getBookItemStatus().equals(BookItem.BookItemStatus.LOANED)) {
            BookLending bookLending =
                    issueRepository.getBookLendingByBookItemId(bookItemId);
            updateBookLendingStatus(bookLending);

            LibraryMember libraryMember
                    = libraryMembersRepository.readByLibraryMemberId(bookLending.getIssuerId());

            countFineIfDue(bookLending.getIssueDate(), libraryMember);
            updateBookNumberOnLibraryMember(libraryMember);
            updateStatusOnBookItem(bookItem);
            notifyIfBookReserved(bookItemId, bookItem);
        }
    }

    private void updateStatusOnBookItem(BookItem bookItem) {
        bookItem.updateStatus(BookItem.BookItemStatus.AVAILABLE);
        bookItemRepository.update(bookItem);
    }

    private void updateBookNumberOnLibraryMember(LibraryMember libraryMember) {
        libraryMember.subtractOneBook();
        libraryMembersRepository.update(libraryMember);
    }

    @Override
    public void cancelReservation(String isbn, UUID issuerId) {
        List<BookItem> bookItems = bookItemRepository.readAvailableByIsbn(isbn);
        if (!bookItems.isEmpty()) {
            List<BookReservation> bookReservations
                    = issueRepository.readSortedListOfPendingBookReservations(isbn);
            BookReservation firstBookReservation = bookReservations.get(0);

            if (firstBookReservation.getIssuerId().equals(issuerId)) {
                firstBookReservation.setReservationStatus(BookReservation.ReservationStatus.EXPIRED);
                issueRepository.updateReservation(firstBookReservation);
                if (bookReservations.size() > 1) {
                    UUID secondIssuerId = bookReservations.get(1).getIssuerId();
                    LibraryMember secondIssuer
                            =
                            libraryMembersRepository.readByLibraryMemberId(secondIssuerId);
                    UUID availableBookId = bookItems.get(0).getId();

                    secondIssuer.sendNotificationBookAvailable(availableBookId);
                }
            }
        }
    }

    private void updateBookLendingStatus(BookLending bookLending) {
        bookLending.setLendingStatus(BookLending.LendingStatus.RETURNED);
        bookLending.setReturnDate(LocalDateTime.now());
        issueRepository.updateLending(bookLending);
    }

    private void countFineIfDue(LocalDateTime issueDate, LibraryMember libraryMember) {
        long lendingTime = DAYS.between(issueDate, LocalDateTime.now());
        if (lendingTime > 10) {
            BigDecimal fine = calculateFine(lendingTime);
            libraryMember.addFine(fine);
            libraryMembersRepository.update(libraryMember);
        }
    }

    private BigDecimal calculateFine(long lendingTime) {
        return BigDecimal.valueOf(lendingTime * ConstantValues.FINE_FOR_ONE_DAY_OF_RENTAL);
    }

    private void notifyIfBookReserved(UUID bookItemId, BookItem bookItem) {
        List<BookReservation> bookReservations =
                issueRepository.
                        readSortedListOfPendingBookReservations(bookItem.getBookIsbn());

        if (!bookReservations.isEmpty()) {
            LibraryMember libraryMember = libraryMembersRepository
                    .readByLibraryMemberId(bookReservations.get(0).getIssuerId());
            libraryMember.sendNotificationBookAvailable(bookItemId);
        }
    }

    private void addNewReservationToIssueRepository(UUID issuerId, String isbn) {
        BookReservation bookReservation = new BookReservation(issuerId, isbn);
        issueRepository.createReservation(bookReservation);
    }


    private void checkIfBookReservedByUser(String isbn, UUID issuerId) {
        List<BookReservation> bookReservations
                = issueRepository.readSortedListOfPendingBookReservations(isbn);
        for (BookReservation bookReservation : bookReservations) {
            if (bookReservation.getIssuerId().equals(issuerId)) {
                throw new IllegalOperationException("You've already reserved " +
                        "this book");
            }
        }
    }

    private LibraryMember checkIfUserExistsAndCanLendMoreBooks(UUID issuerId) {
        LibraryMember libraryMember = libraryMembersRepository.readByLibraryMemberId(issuerId);
        if (libraryMember.getTotalBooksCheckedOut() >= 5) {
            throw new IllegalOperationException("You cannot lend book - book " +
                    "limit reached");
        }
        return libraryMember;
    }

    private void addOneBookToLibraryMember(LibraryMember libraryMember) {
        libraryMember.addOneBook();
        libraryMembersRepository.update(libraryMember);
    }

    private void createNewBookLending(String isbn, UUID issuerId) {
        UUID bookId =
                getAvaliableBookId(isbn);
        updateBookStatus(BookItem.BookItemStatus.LOANED, bookId);
        BookLending bookLending = new BookLending(bookId, issuerId);
        issueRepository.createLending(bookLending);
    }

    private void verifyAndUpdateReservations(String isbn, UUID issuerId) {

        List<BookReservation> sortedListOfActiveBookReservations =
                issueRepository.readSortedListOfPendingBookReservations(isbn);
        if (!sortedListOfActiveBookReservations.isEmpty()) {
            BookReservation bookReservation =
                    sortedListOfActiveBookReservations.get(0);
            if (!bookReservation.getIssuerId().equals(issuerId)) {
                throw new IllegalOperationException("Book reserved by another" +
                        " user");
            } else {
                setExpiredStatusAndUpdateIssueRepository(bookReservation);
            }
        }
    }

    private void setExpiredStatusAndUpdateIssueRepository(BookReservation bookReservation) {
        bookReservation.setExpiredStatus();
        issueRepository.updateReservation(bookReservation);
    }

    private boolean checkIfBookIsAvailable(String isbn) {
        return !bookItemRepository.readAvailableByIsbn(isbn).isEmpty();
    }

    private void checkIfUserLoanedBookOfThisIsbnBefore(String isbn, UUID issuerId) {
        List<BookItem> bookItems = bookItemRepository.readLoanedByIsbn(isbn);

        if (!bookItems.isEmpty()) {
            for (BookItem bookItem : bookItems) {
                UUID lenderIdByBookItemId =
                        issueRepository.readLenderIdByBookItemId(bookItem.getId());
                if (!(lenderIdByBookItemId == null)) {
                    if (lenderIdByBookItemId.equals(issuerId)) {
                        throw new IllegalOperationException("You already lended " +
                                "book of this ISBN");
                    }
                }
            }
        }
    }


    private UUID getAvaliableBookId(String isbn) {
        List<BookItem> bookItems = bookItemRepository.readAvailableByIsbn(isbn);
        return bookItems.get(0).getId();
    }


    @Override
    public void updateBookStatus(BookItem.BookItemStatus bookStatus,
                                 UUID bookId) {
        BookItem bookItem = bookItemRepository.readByBookItemId(bookId);
        bookItem.updateStatus(bookStatus);
        bookItemRepository.update(bookItem);
    }


    @Override
    public String toString() {
        return "IssueService{" + issueRepository +
                '}';
    }
}
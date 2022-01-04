package com.amadeusz.library.application.service;

import com.amadeusz.library.application.ConstantValues;
import com.amadeusz.library.application.model.accounts.librarymembers.LibraryMember;
import com.amadeusz.library.infrastructure.repository.BookLendingJpaRepository;
import com.amadeusz.library.infrastructure.repository.BookReservationJpaRepository;
import com.amadeusz.library.application.model.bookissue.BookLending;
import com.amadeusz.library.application.model.bookissue.BookReservation;
import com.amadeusz.library.application.model.bookitem.BookItem;
import com.amadeusz.library.application.exceptions.IllegalOperationException;
import com.amadeusz.library.infrastructure.repository.entities.BookLendingEntity;
import com.amadeusz.library.infrastructure.repository.entities.BookReservationEntity;
import com.amadeusz.library.infrastructure.repository.entities.mappers.BookIssueMapper;
import com.amadeusz.library.infrastructure.repository.entities.mappers.DefaultBookIssueEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service("issueService")
public class IssueServiceImpl implements IssueService {

    @Autowired
    private BookReservationJpaRepository reservationRepository;

    @Autowired
    private BookLendingJpaRepository lendingRepository;

    @Autowired
    private BookItemService bookItemService;

    @Autowired
    private LibraryMembersService libraryMembersService;

    private final BookIssueMapper mapper = new DefaultBookIssueEntityMapper();


    @Override
    public BookLending lendBook(String isbn, UUID issuerId) {
        LibraryMember libraryMember = checkIfUserExistsAndCanLendMoreBooks(issuerId);
        checkIfUserLoanedBookOfThisIsbnBefore(isbn, issuerId);
        if (!checkIfBookIsAvailable(isbn)) {
            throw new IllegalOperationException("Book is not available");
        }
        verifyAndUpdateReservations(isbn, issuerId);
        addOneBookToLibraryMember(libraryMember);
        return createNewBookLending(isbn, issuerId);
    }

    @Override
    public BookReservation reserveBook(String isbn, UUID issuerId) {
        if (checkIfBookIsAvailable(isbn)) {
            throw new IllegalOperationException("You cannot reserve book that" + " is available to lend");
        }
        checkIfBookReservedByUser(isbn, issuerId);
        return addNewReservationToIssueRepository(issuerId, isbn);
    }

    @Override
    public BookLending returnBook(UUID bookItemId) {
        BookItem bookItem = bookItemService.getById(bookItemId);
        if (bookItem.getBookItemStatus().equals(BookItem.BookItemStatus.LOANED)) {
            BookLendingEntity bookLendingEntity = lendingRepository.findByBookItemId(bookItemId);
            BookLending bookLending = mapper.map(bookLendingEntity);
            LibraryMember libraryMember = libraryMembersService.getById(bookLending.getIssuerId());
            countFineIfDue(bookLending.getIssueDate(), libraryMember);
            updateBookNumberOnLibraryMember(libraryMember);
            updateStatusOnBookItem(bookItem);
            notifyIfBookReserved(bookItemId, bookItem);
            return updateBookLendingStatus(bookLending);
        }
        else {
            throw new IllegalOperationException("Book was not lended yet");
        }
    }

    @Override
    public BookReservation cancelReservation(String isbn, UUID issuerId) {
        List<BookItem> bookItems = bookItemService.readAvailableByIsbn(isbn);
        if (!bookItems.isEmpty()) {
            List<BookReservationEntity> pending =
                    reservationRepository.findByBookIsbnAndReservationStatusOrderByIssueDateAsc(isbn, "PENDING");
            List<BookReservation> bookReservations = pending.stream().map(mapper::map).collect(Collectors.toList());
            BookReservation firstBookReservation = bookReservations.get(0);
            if (firstBookReservation.getIssuerId().equals(issuerId)) {
                firstBookReservation.setReservationStatus(BookReservation.ReservationStatus.EXPIRED);
                if (bookReservations.size() > 1) {
                    UUID secondIssuerId = bookReservations.get(1).getIssuerId();
                    LibraryMember secondIssuer = libraryMembersService.getById(secondIssuerId);
                    UUID availableBookId = bookItems.get(0).getId();
                    secondIssuer.sendNotificationBookAvailable(availableBookId);
                }
                BookReservationEntity bookReservationEntity =
                        reservationRepository.saveAndFlush(mapper.map(firstBookReservation));
                return mapper.map(bookReservationEntity);
            }
        } else {
            throw new IllegalOperationException("Book not in repository");
        }
        return null;
    }

    private void updateStatusOnBookItem(BookItem bookItem) {
        bookItem.setBookItemStatus(BookItem.BookItemStatus.AVAILABLE);
        bookItemService.updateBook(bookItem);
    }

    private void updateBookNumberOnLibraryMember(LibraryMember libraryMember) {
        libraryMember.subtractOneBook();
        libraryMembersService.update(libraryMember);
    }

    private BookLending updateBookLendingStatus(BookLending bookLending) {
        bookLending.setLendingStatus(BookLending.LendingStatus.RETURNED);
        bookLending.setReturnDate(LocalDateTime.now());
        BookLendingEntity bookLendingEntity = lendingRepository.saveAndFlush(mapper.map(bookLending));
        return mapper.map(bookLendingEntity);
    }

    private void countFineIfDue(LocalDateTime issueDate, LibraryMember libraryMember) {
        long lendingTime = DAYS.between(issueDate, LocalDateTime.now());
        if (lendingTime > 10) {
            BigDecimal fine = calculateFine(lendingTime);
            libraryMember.addFine(fine);
            libraryMembersService.update(libraryMember);
        }
    }

    private BigDecimal calculateFine(long lendingTime) {
        return BigDecimal.valueOf(lendingTime * ConstantValues.FINE_FOR_ONE_DAY_OF_RENTAL);
    }

    private void notifyIfBookReserved(UUID bookItemId, BookItem bookItem) {
        List<BookReservationEntity> pending =
                reservationRepository.findByBookIsbnAndReservationStatusOrderByIssueDateAsc(bookItem.getBookIsbn(),
                        "PENDING");

        List<BookReservation> bookReservations
                = pending.stream().map(mapper::map).collect(Collectors.toList());

        if (!bookReservations.isEmpty()) {
            LibraryMember libraryMember
                    = libraryMembersService.getById(bookReservations.get(0).getIssuerId());
            libraryMember.sendNotificationBookAvailable(bookItemId);
        }
    }

    private BookReservation addNewReservationToIssueRepository(UUID issuerId, String isbn) {
        BookReservation bookReservation = new BookReservation(issuerId, isbn);
        BookReservationEntity bookReservationEntity = reservationRepository.saveAndFlush(mapper.map(bookReservation));
        return mapper.map(bookReservationEntity);
    }


    private void checkIfBookReservedByUser(String isbn, UUID issuerId) {
        List<BookReservationEntity> pending =
                reservationRepository.findByBookIsbnAndReservationStatusOrderByIssueDateAsc(isbn, "PENDING");
        List<BookReservation> bookReservations = pending.stream().map(mapper::map).collect(Collectors.toList());
        for (BookReservation bookReservation : bookReservations) {
            if (bookReservation.getIssuerId().equals(issuerId)) {
                throw new IllegalOperationException("You've already reserved " + "this book");
            }
        }
    }

    private LibraryMember checkIfUserExistsAndCanLendMoreBooks(UUID issuerId) {
        LibraryMember libraryMember = libraryMembersService.getById(issuerId);
        if (libraryMember.getTotalBooksCheckedOut() >= 5) {
            throw new IllegalOperationException("You cannot lend book - book " + "limit reached");
        }
        return libraryMember;
    }

    private void addOneBookToLibraryMember(LibraryMember libraryMember) {
        libraryMember.addOneBook();
        libraryMembersService.update(libraryMember);
    }

    private BookLending createNewBookLending(String isbn, UUID issuerId) {
        UUID bookId = getAvailableBookId(isbn);
        updateBookStatus(BookItem.BookItemStatus.LOANED, bookId);
        BookLending bookLending = new BookLending(bookId, issuerId);
        BookLendingEntity bookLendingEntity = lendingRepository.saveAndFlush(mapper.map(bookLending));
        return mapper.map(bookLendingEntity);
    }

    private void verifyAndUpdateReservations(String isbn, UUID issuerId) {
        List<BookReservationEntity> pending =
                reservationRepository.findByBookIsbnAndReservationStatusOrderByIssueDateAsc(isbn, "PENDING");

        List<BookReservation> sortedListOfActiveBookReservations =
                pending.stream().map(mapper::map).collect(Collectors.toList());
        if (!sortedListOfActiveBookReservations.isEmpty()) {
            BookReservation bookReservation = sortedListOfActiveBookReservations.get(0);
            if (!bookReservation.getIssuerId().equals(issuerId)) {
                throw new IllegalOperationException("Book reserved by another" + " user");
            } else {
                setExpiredStatusAndUpdateIssueRepository(bookReservation);
            }
        }
    }

    private void setExpiredStatusAndUpdateIssueRepository(BookReservation bookReservation) {
        bookReservation.setExpiredStatus();
        reservationRepository.saveAndFlush(mapper.map(bookReservation));
    }

    private boolean checkIfBookIsAvailable(String isbn) {
        List<BookItem> bookItems = bookItemService.readAvailableByIsbn(isbn);
        return !bookItems.isEmpty();
    }

    private void checkIfUserLoanedBookOfThisIsbnBefore(String isbn, UUID issuerId) {
        List<BookItem> bookItems = bookItemService.readLoanedByIsbn(isbn);

        if (!bookItems.isEmpty()) {
            for (BookItem bookItem : bookItems) {
                UUID lenderIdByBookItemId = lendingRepository.findByBookItemId(bookItem.getId()).getIssuerId();
                if (!(lenderIdByBookItemId == null)) {
                    if (lenderIdByBookItemId.equals(issuerId)) {
                        throw new IllegalOperationException("You already loaned " + "book of this ISBN");
                    }
                }
            }
        }
    }


    private UUID getAvailableBookId(String isbn) {
        List<BookItem> bookItems = bookItemService.readAvailableByIsbn(isbn);
        return bookItems.get(0).getId();
    }

    public void updateBookStatus(BookItem.BookItemStatus bookStatus, UUID bookId) {
        BookItem bookItem = bookItemService.getById(bookId);
        bookItem.setBookItemStatus(bookStatus);
        bookItemService.updateBook(bookItem);
    }

}
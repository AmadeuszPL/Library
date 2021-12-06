package com.amadeusz.library.application.bookitem;
import com.amadeusz.library.application.book.Book;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

public class BookItem implements Comparable<BookItem> {

    private final UUID id;
    private final String bookIsbn;
    private final RackNumber rackNumber;
    private BookItemStatus bookItemStatus;


    public BookItem(Book book, RackNumber rackNumber) {
        String isbnStringValue = book.getIsbn().getValue();
        this.id = UUID.randomUUID();
        this.bookIsbn = isbnStringValue;
        this.rackNumber = rackNumber;
        this.bookItemStatus = BookItemStatus.AVAILABLE;
    }

    public BookItem(UUID bookItemId, String bookIsbn, String rackNumber,
                    String bookItemStatus) {
        this.id = bookItemId;
        this.bookIsbn = bookIsbn;
        this.rackNumber = new RackNumber(rackNumber);
        this.bookItemStatus = BookItemStatus.valueOf(bookItemStatus);
    }

    public UUID getId() {
        return id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public RackNumber getRackNumber() {
        return rackNumber;
    }

    public BookItemStatus getBookItemStatus() {
        return bookItemStatus;
    }

    public void updateStatus(BookItemStatus status){
        this.bookItemStatus = status;
    }

    public void changeStatusToLended() {
        this.bookItemStatus = BookItemStatus.LOANED;
    }

    public void changeStatusToAvailable(){
        this.bookItemStatus = BookItemStatus.AVAILABLE;
    }

    public void generateAndPrintBarcodeToFile(String isbn) throws Exception {
        Barcode barcode = BarcodeFactory.createCode128(isbn);
        BufferedImage image = BarcodeImageHandler.getImage(barcode);
        File outputfile =
                new File("barcodes/" + bookIsbn + ".png");
        ImageIO.write(image, "png", outputfile);
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id +
                ", bookIsbn='" + bookIsbn + '\'' +
                '}';
    }

    @Override
    public int compareTo(BookItem bookItem) {
        return this.getBookIsbn().compareTo(bookItem.getBookIsbn());
    }

    public enum BookItemStatus {

        AVAILABLE,
        LOANED

    }


}

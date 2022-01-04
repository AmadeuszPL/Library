package com.amadeusz.library.application.model.bookitem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class BookItem implements Comparable<BookItem> {

    private final UUID id;
    private final String bookIsbn;
    private RackNumber rackNumber;
    private BookItemStatus bookItemStatus;

    public BookItem(@JsonProperty("bookIsbn") String bookIsbn,
                    @JsonProperty("rackNumber") String rackNumber){
        this.id = UUID.randomUUID();
        this.bookIsbn = bookIsbn.replaceAll("[^0-9]", "");
        this.rackNumber = new RackNumber(rackNumber);
        this.bookItemStatus = BookItemStatus.AVAILABLE;
    }

    public BookItem(UUID id, String bookIsbn, RackNumber rackNumber, BookItemStatus bookItemStatus) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.rackNumber = rackNumber;
        this.bookItemStatus = bookItemStatus;
    }

/*    public void generateAndPrintBarcodeToFile(String isbn) throws Exception {
        Barcode barcode = BarcodeFactory.createCode128(isbn);
        BufferedImage image = BarcodeImageHandler.getImage(barcode);
        File outputfile = new File("barcodes/" + bookIsbn + ".png");
        ImageIO.write(image, "png", outputfile);
    }*/

    @Override
    public String toString() {
        return "BookItem{" + "id=" + id + ", bookIsbn='" + bookIsbn + '\'' + '}';
    }

    @Override
    public int compareTo(BookItem bookItem) {
        return this.getBookIsbn().compareTo(bookItem.getBookIsbn());
    }

    public enum BookItemStatus {
        AVAILABLE, LOANED
    }


}

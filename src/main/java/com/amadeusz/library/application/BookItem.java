package com.amadeusz.library.application;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class BookItem implements Comparable<BookItem> {

    private final UUID id;
    private final String bookIsbn;
    private final BufferedImage barcode;
    private final RackNumber rackNumber;

    public BookItem(Book book, RackNumber rackNumber) throws Exception {
        String isbnStringValue = book.getIsbn().getValue();
        this.id = UUID.randomUUID();
        this.bookIsbn = isbnStringValue;
        this.barcode = generateCode128BarcodeImage(isbnStringValue);
        this.rackNumber = rackNumber;
    }

    public BookItem(UUID bookItemId, String bookIsbn, String rackNumber) throws Exception{
        this.id = bookItemId;
        this.bookIsbn = bookIsbn;
        this.barcode = generateCode128BarcodeImage(bookIsbn);
        this.rackNumber = new RackNumber(rackNumber);
    }

    public UUID getId() {
        return id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public BufferedImage getBarcode() {
        return barcode;
    }

    public RackNumber getRackNumber() {
        return rackNumber;
    }

    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createCode128(barcodeText);
        return BarcodeImageHandler.getImage(barcode);
    }

    public void printBarcodeToFile() throws IOException {
        File outputfile =
                new File("barcodes/" + bookIsbn + ".png");
        ImageIO.write(barcode, "png", outputfile);
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


}

package com.amadeusz.library;

import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeFactory;
import net.sourceforge.barbecue.BarcodeImageHandler;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

class BookItem {

    private final UUID id;
    private final Book book;
    private final BufferedImage barcode;
    private final RackNumber rackNumber;

    BookItem(Book book, RackNumber rackNumber) throws Exception {
        id = UUID.randomUUID();
        this.book = book;
        this.barcode = generateCode128BarcodeImage(book.getIsbn().getValue());
        this.rackNumber = rackNumber;
    }

    public UUID getId() {
        return id;
    }

    public Book.SubjectCategory getCategory() {
        return book.getCategory();
    }

    public String getTitle() {
        return book.getTitle();
    }

    public String getAuthor() {
        return book.getAuthor().getName();
    }

    public int getYear() {
        return book.getPublicationYear();
    }


    public static BufferedImage generateCode128BarcodeImage(String barcodeText) throws Exception {
        Barcode barcode = BarcodeFactory.createCode128(barcodeText);
        return BarcodeImageHandler.getImage(barcode);
    }

    public void printBarcodeToFile() throws IOException {
        File outputfile =
                new File("barcodes/" + book.getIsbn().getValue() + ".png");
        ImageIO.write(barcode, "png", outputfile);
    }

    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id + book +
                "}\n";
    }
}

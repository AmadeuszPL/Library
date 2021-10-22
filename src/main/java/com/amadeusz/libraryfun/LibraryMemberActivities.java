package com.amadeusz.libraryfun;

import java.util.Map;

public interface LibraryMemberActivities {
    boolean checkOutBookByISBN(Map<Double, BookItem> db, double barcode);
    void reserveBookByISBN(Map<Double, BookItem> db, double barcode);
}

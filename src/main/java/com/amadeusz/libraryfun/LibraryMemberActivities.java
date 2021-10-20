package com.amadeusz.libraryfun;

import java.util.Map;

public interface LibraryMemberActivities {
    void checkOutBookByISBN(Map<String, BookItem> db, String ISBN);
    void reserveBookByISBN(Map<String, BookItem> db, String ISBN);
}

package com.amadeusz.libraryfun;

public class RackNumber {
    private String locationCode;

    public RackNumber(String locationCode) {
        this.locationCode = locationCode;
    }

    void printBookLocation(){
        System.out.println("Room: " + locationCode.substring(0,2) + " " +
                "Bookcase: " + locationCode.substring(2,4)
                + " Shelf: " + locationCode.substring(4,6)  );
    }
}

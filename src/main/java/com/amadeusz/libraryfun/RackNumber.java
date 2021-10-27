package com.amadeusz.libraryfun;

import org.apache.commons.lang3.math.NumberUtils;

class RackNumber {
    private String locationCode;

    RackNumber(String locationCode) {
        verifyRackNumber(locationCode);
        this.locationCode = locationCode;
    }

    private void verifyRackNumber(String locationCode) {
        if (locationCode.length() != 6 | !NumberUtils.isParsable(locationCode)) {
            throw new RuntimeException("Wrong Rack Number");
        }
    }

    @Override
    public String toString() {
        return "RackNumber{" +
                "Room: " + locationCode.substring(0, 2) + " " +
                "Bookcase: " + locationCode.substring(2, 4)
                + " Shelf: " + locationCode.substring(4, 6) + +
                '}';
    }
}

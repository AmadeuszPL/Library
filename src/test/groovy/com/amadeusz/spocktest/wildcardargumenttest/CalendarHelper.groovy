package com.amadeusz.spocktest.wildcardargumenttest

class CalendarHelper {

    CalendarClient client

    void book(String message) {
        client.bookTimeSlot message
    }
}

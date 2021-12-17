package com.amadeusz.spocktest.wildcardargumenttest

import spock.lang.Specification


class CalendarHelperSpec extends Specification{

    def "test a basic mock"() {
        given:
        def helper = new CalendarHelper()
        def mockGoogleCalendarClient = Mock(CalendarClient)
        helper.client = mockGoogleCalendarClient

        when:
        helper.book 'Some Booking Message'

        then:
        1 * mockGoogleCalendarClient.bookTimeSlot(_)
    }

}

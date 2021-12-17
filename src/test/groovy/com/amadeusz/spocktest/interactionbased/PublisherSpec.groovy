package com.amadeusz.spocktest.interactionbased

import spock.lang.Specification

class PublisherSpec extends Specification {

    Publisher pub
    Subscriber sub1
    Subscriber sub2

    def setup() {
        pub = new Publisher()
        sub1 = Mock(Subscriber)
        sub2 = Mock(Subscriber)
        pub.subscribers << sub1
        pub.getSubscribers().add(sub2)
    }

    def "delivers events to all subscribers"() {
        when:
        pub.send("event")

        then:
        1 * sub1.receive(_)
        1 * sub2._
    }


    def "can cope with misbehaving subscribers"() {
        sub1.receive(_) >> { throw new Exception() }

        when:
        pub.send("event1")
        pub.send("event2")

        then:
        1 * sub2.receive("event1")
        1 * sub2.receive("event2")
    }


}

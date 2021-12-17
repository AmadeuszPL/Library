package com.amadeusz.spocktest

import spock.lang.Specification

class PublisherSpecification extends Specification{


    def "check events are published to all subscribers"() {

        given:
        Publisher publisher = new Publisher()
        def subscriber = Mock(Subscriber)
        def subscriber2 = Mock(Subscriber)
        def id = UUID.randomUUID()
        Event event = new Event(id, "Testujemy")

        publisher.add(subscriber)
        publisher.add(subscriber2)

        when:
        publisher.fire(event)

        then:
        1 * subscriber.receive({Event e -> assert e.getTitle() == "Testujemy"})
        1 * subscriber2.receive({ Event e -> assert e.getId() == id })

    }
}

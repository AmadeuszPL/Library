package com.amadeusz.spocktest


import spock.lang.Specification

class FirstSpecification extends Specification {

    Shop shop
    def stack;
    static final PI = 3.141592654


    // runs once - before the first feature method
    def setupSpec() {
//        @Shared res = new VeryExpensiveResuorce()
    }
    //runs before every feature method
    def setup() {
        shop = Stub(Shop)
        stack = new Stack()
        assert stack.empty
    }

    def "pushing an element on the stack"() {
        given:
//        def stack = new Stack()
        def elem = "push me"

        when:
        stack.push(elem)

        then:
        !stack.empty()
        stack.size() == 1
        stack.get(0) == "push me"
        stack.peek() == elem
    }

    def "emptying an empty stack"() {
        when:
        stack.pop()

        //then a EmptyStackException is thrown
        then:
        EmptyStackException e = thrown()
        //alternatively
        //def e = thrown(EmptyStackException)
        e.cause == null
    }

    def "HashMap accepts null key"() {
        given:
        def map = new HashMap()

        when:
        map.put(null, "elem")

        //make sure in particular a NullPointerException
        //should not be thrown
        then:
        notThrown(NullPointerException)
    }

    //interactions
    def "events are published to all subscribers"() {
        given:
        def subscriber1 = Mock(Subscriber)
        def subscriber2 = Mock(Subscriber)
        def publisher = new Publisher()

        def event = Mock(Event)

        publisher.add(subscriber1)
        publisher.add(subscriber2)

        when:
        publisher.fire(event)

        then:
        1 * subscriber1.receive(event)
        1 * subscriber2.receive(event)
    }

    //expect blocks
    def "check if number calculates math max correctly"() {

/*        when:
        def x = Math.max(1,2)

        then:
        x == 2*/

        //above version is equivalent, but below is preferable
        expect:
        Math.max(1, 2) == 2
    }

    //where blocks
    def "computing the maximum of two numbers"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [5, 3]
        b << [1, 9]
        c << [5, 9]
    }

    //helper methods
    def "offered PC matches preferred configuration"() {
        given:
        shop.buyPc() >> new Pc("Sunny", 2333, 4096, "Linux")

        when:
        def pc = shop.buyPc()

        then:
        matchesPrefferedConfiguration(pc)
    }

    private void matchesPrefferedConfiguration(Pc pc) {
        assert pc.vendor == "Sunny"
        assert pc.clockRate >= 2333
        assert pc.ram >= 4096
        assert pc.os == "Linux"
    }

    //using with for expectations
    def "offered PC matches preferred configuration using with"() {
        given:
        shop.buyPc() >> new Pc("Sunny", 2333, 4096, "Linux")

        when:
        def pc = shop.buyPc()

        then:
        with(pc) {
            vendor == "Sunny"
            clockRate >= 2333
            ram >= 4096
            os == "Linus"
        }
    }

    def "should correctly count sum of two numbers"(){
        expect:
        d == a

        where:
        a | b
        1 | a + 1
        7 | a + 2
        0 | a + 3

        c << [3, 4, 0]

        d = a > c ? a : c
    }

    def "type coercion for data variable values"(Integer i) {
        expect:
        i instanceof Integer
        i == 10

        where:
        i = "10"
    }


    def "running application starts services"() {
        given: "Mocked service"
        def service = Mock(Service)
        def app = new Application(service)

        when: "Application runs twice"
        app.run()
        app.run()

        then: "Service should run processes twice"
        with(service) {
            2 * start()
            2 * doWork()
            2 * stop()
        }
    }

    def "offered PC matches preferred configuration verifyAll test"() {
        given:
        shop.buyPc() >> new Pc("Sunny", 2333, 4096, "Linux")

        when:
        def pc = shop.buyPc()

        then:
        verifyAll(pc) {
            vendor == "Sunny"
            clockRate >= 2333
            ram >= 4096
            os == "Linus"
        }
    }

    def "maximum of two numbers"() {
        expect:
        // exercise math method for a few different inputs
        Math.max(1, 3) == 3
        Math.max(7, 4) == 7
        Math.max(0, 0) == 0
    }

    def "maximum of two numbers"(int a, int b, int c) {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    def "maximum of two numbers using Data Pipes"() {
        expect:
        Math.max(a, b) == c

        where:
        a << [1, 7, 0]
        b << [3, 4, 0]
        c << [3, 7, 0]
    }




    def "one plus one should equal two"() {
        expect:
        1 + 1 == 2
    }

    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }

    def "Should be able to remove from list"() {
        given:
        def list = [1, 2, 3, 4]

        when:
        list.remove(0)

        then:
        list == [2, 3, 4]
    }

    def "numbers to the power of two"(int a, int b, int c) {
        expect:
        StrictMath.pow(a, b) == c

        where:
        a | b | c
        1 | 2 | 1
        2 | 4 | 16
        3 | 2 | 9
    }

    //runs after every feature method
    def cleanup() {}
    //runs once - after the last feature method
    def cleanupSpec() {}

}
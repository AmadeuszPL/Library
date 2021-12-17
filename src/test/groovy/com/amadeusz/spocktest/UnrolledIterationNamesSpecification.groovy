package com.amadeusz.spocktest

import spock.lang.Specification

class UnrolledIterationNamesSpecification extends Specification {

    def "maximum of #a and #b is #c"() {
        expect:
        Math.max(a, b) == c

        where:
        a | b || c
        1 | 3 || 3
        7 | 5 || 7
        8 | 9 || 9
        0 | 0 || 5
    }

    def "#person.name is a #sex.toLowerCase() person"() {
        expect:
        person.getSex() == sex

        where:
        person                    || sex
        new Person("Fred")  || "Male"
        new Person("Wilma") || "Female"
    }

    def "#person.name is #person.age years old"() {
        expect:
        person.getAge() == age

        where:
        person || age
        new Person("Franek") || 16
        new Person("Dzbanek") || 25
        new Person("Czajnik") || 25
    }


}

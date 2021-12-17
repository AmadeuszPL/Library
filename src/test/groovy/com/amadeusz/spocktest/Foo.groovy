package com.amadeusz.spocktest

import spock.lang.Specification
import spock.util.mop.Use

@Use(CoerceBazToBar)
class Foo extends Specification
{
    def foo(Bar bar) {
        expect:
        bar == Bar.FOO

        where:
        bar = Baz.FOO
    }
}
enum Bar { FOO, BAR }
enum Baz { FOO, BAR }
class CoerceBazToBar {
    static Bar asType(Baz self, Class<Bar> clazz) {
        return Bar.valueOf(self.name())
    }
}

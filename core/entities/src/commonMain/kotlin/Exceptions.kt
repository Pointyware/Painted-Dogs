package org.pointyware.painteddogs.core.entities


open class BlankStringArgumentException(name: String, cause: Throwable? = null): IllegalArgumentException("$name can not be blank.", cause)
open class NumberArgumentException(name: String, expectation: String, actual: Number, cause: Throwable? = null): IllegalArgumentException("$name violated expectation $expectation: actual $actual", cause) {
    open class ConditionException(name:String, condition: String, expected: Number, actual: Number, cause: Throwable? = null): NumberArgumentException(name, "$condition $expected", actual, cause)
    class LessThan(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "less than", expected, actual, cause)
    class GreaterThan(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "greater than", expected, actual, cause)
    class AtMost(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "at most", expected, actual, cause)
    class AtLeast(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "at least", expected, actual, cause)
}

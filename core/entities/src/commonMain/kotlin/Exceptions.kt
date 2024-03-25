package org.pointyware.painteddogs.core.entities

open class StringArgumentException(message: String?, cause: Throwable? = null): IllegalArgumentException(message, cause) {
    class BlankStringArgumentException(name: String, cause: Throwable? = null): StringArgumentException("$name can not be blank.", cause)
    open class LengthArgumentException(name: String, expectation: String, actual: Int, cause: Throwable? = null): StringArgumentException("$name violated was not $expectation: actual $actual", cause) {
        open class ConditionException(name:String, condition: String, expected: Number, actual: Number, cause: Throwable? = null): NumberArgumentException(name, "$condition $expected", actual, cause)
        class LessThan(expected: Int, name: String, actual: Int, cause: Throwable? = null): ConditionException(name, "less than", expected, actual, cause)
        class GreaterThan(expected: Int, name: String, actual: Int, cause: Throwable? = null): ConditionException(name, "greater than", expected, actual, cause)
        class AtMost(expected: Int, name: String, actual: Int, cause: Throwable? = null): ConditionException(name, "at most", expected, actual, cause)
        class AtLeast(expected: Int, name: String, actual: Int, cause: Throwable? = null): ConditionException(name, "at least", expected, actual, cause)
    }
}
open class NumberArgumentException(name: String, expectation: String, actual: Number, cause: Throwable? = null): IllegalArgumentException("$name violated expectation $expectation: actual $actual", cause) {
    open class ConditionException(name:String, condition: String, expected: Number, actual: Number, cause: Throwable? = null): NumberArgumentException(name, "$condition $expected", actual, cause)
    class LessThan(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "less than", expected, actual, cause)
    class GreaterThan(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "greater than", expected, actual, cause)
    class AtMost(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "at most", expected, actual, cause)
    class AtLeast(expected: Number, name: String, actual: Number, cause: Throwable? = null): ConditionException(name, "at least", expected, actual, cause)
}

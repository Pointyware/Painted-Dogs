package org.pointyware.painteddogs.assertions

import kotlin.test.assertTrue


class Subject<T>(private val subject: T) {
    fun isNotIn(items: Collection<T>) {
        assertTrue(subject !in items, "$subject is in $items")
    }

    fun isEqualTo(other: Any) {
        assertTrue(subject == other, "$subject is not equal to $other")
    }

    fun isNull() {
        assertTrue(subject == null, "$subject is not null")
    }

    fun isTrue() {
        assertTrue(subject == true, "$subject is not true")
    }
}
fun <T> assertThat(subject: T): Subject<T> {
    return Subject(subject)
}

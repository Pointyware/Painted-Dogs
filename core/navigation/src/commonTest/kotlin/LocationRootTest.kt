package org.pointyware.painteddogs.core.navigation

import kotlin.math.exp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 *
 */

class RouteTest() {
    @Test
    fun instances_with_same_segments_are_equal() {
        val route1 = route("a", "b", "c")
        val route2 = route("a", "b", "c")
        assertEquals(route1, route2)
    }

    @Test
    fun equal_distinct_routes_have_same_hashcode() {
        val route1 = route("a", "b", "c")
        val route2 = route("a", "b", "c")
        assertEquals(route1.hashCode(), route2.hashCode())
    }

    @Test
    fun equal_distinct_routes_are_equal_keys_in_maps() {
        val route1 = route("a", "b", "c")
        val route2 = route("a", "b", "c")
        val expectedValue = "value"
        val map = mapOf(route1 to expectedValue)
        val actualValue = map[route2]
        assertEquals(expectedValue, actualValue)
    }
}

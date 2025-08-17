package org.pointyware.painteddogs.core.navigation
/**
 * Supporting interface for LocationRootScope to allow type-safe or string paths with
 * `LocationRootScope<org.pointyware.painteddogs.core.navigation.Route<Any>, Any>` or `LocationRootScope<String, Any>`.
 *
 * ```kotlin
 * class TypeA(val name: String): org.pointyware.painteddogs.core.navigation.Segment.Static {
 *   inner class TypeB(val name: String): org.pointyware.painteddogs.core.navigation.Segment.Variable {
 *
 *   }
 * }
 * ```
 *
 * ```kotlin
 * val org.pointyware.painteddogs.core.navigation.route = TypeA("some").TypeB("userId")
 * ```
 */
@Deprecated("Replaced by multiplatform navigation")
interface Route<S> {
    val segments: List<S>
}

@Deprecated("Replaced by multiplatform navigation")
data class SegmentList<S>(override val segments: List<S>): Route<S> {
    operator fun plus(segment: S): SegmentList<S> {
        return SegmentList(segments + segment)
    }
}

@Deprecated("Replaced by multiplatform navigation")
sealed interface Segment {
    val name: String
    data class Static(override val name: String): Segment
    data class Variable(override val name: String): Segment
}

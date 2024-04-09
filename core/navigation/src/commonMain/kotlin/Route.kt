
/**
 * Supporting interface for LocationRootScope to allow type-safe or string paths with
 * `LocationRootScope<Route<Any>, Any>` or `LocationRootScope<String, Any>`.
 *
 * ```kotlin
 * class TypeA(val name: String): Segment.Static {
 *   inner class TypeB(val name: String): Segment.Variable {
 *
 *   }
 * }
 * ```
 *
 * ```kotlin
 * val route = TypeA("some").TypeB("userId")
 * ```
 */
interface Route<S> {
    val segments: List<S>
}

data class SegmentList<S>(override val segments: List<S>): Route<S> {
    operator fun plus(segment: S): SegmentList<S> {
        return SegmentList(segments + segment)
    }
}

/**
 * Replace `location("some/path") {` with `location(route("some", "path")) {`
 */
fun <S> route(vararg segments: S): Route<S> {
    return SegmentList(segments.toList())
}

sealed interface Segment {
    val name: String
    data class Static(override val name: String): Segment
    data class Variable(override val name: String): Segment
}
fun static(name: String): Segment = Segment.Static(name)
fun variable(name: String): Segment = Segment.Variable(name)

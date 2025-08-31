package org.pointyware.painteddogs.aid.entities

/**
 * Consider merging [Schedule] and [Event]. The only difference between the two is
 * an event happens once and schedule (as of now) happens more than once. Both
 * can be defined as a list of dates or date/week patterns.
 *
 * [Immediate], [Limited], and [Indefinite] all begin at the moment of posting,
 * with the only difference between each being the urgency of the request.
 *
 */
sealed interface TemporalScope {
    /**
     * An exchange available at the time of posting - Least urgent
     */
    data object Indefinite: TemporalScope

    /**
     * An exchange available at the time of posting - Somewhat urgent
     */
    data object Limited: TemporalScope

    /**
     * An exchange available at the time of posting - Highest urgency
     */
    data object Immediate: TemporalScope

    /**
     * An exchange available at a later time.
     */
    data object Event: TemporalScope

    /**
     * An exchange available at later multiple times.
     */
    data object Schedule: TemporalScope

    companion object {
        val entries: List<TemporalScope>
            get() = listOf(Indefinite, Limited, Immediate, Event, Schedule)
    }
}

package org.pointyware.painteddogs.aid.entities

sealed interface TemporalScope {
    data object Indefinite: TemporalScope
        data object Limited: TemporalScope
    data object Schedule: TemporalScope
    data object Event: TemporalScope
    data object Immediate: TemporalScope

    companion object {
        val entries: List<TemporalScope>
            get() = listOf(Indefinite, Limited, Schedule, Event, Immediate)
    }
}

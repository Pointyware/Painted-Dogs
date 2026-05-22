package org.pointyware.painteddogs.common

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number

/**
 * Shift the receiver [LocalDateTime] by the given date-time coordinates.
 */
fun LocalDateTime.later(
    year: Int = 0,
    month: Int = 0,
    day: Int = 0,
    hour: Int = 0,
    minute: Int = 0,
    second: Int = 0
): LocalDateTime {
    val date = this.date
    val time = this.time
    return LocalDateTime(
        year = date.year + year,
        month = date.month.number + month,
        day = date.day + day,
        hour = time.hour + hour,
        minute = time.minute + minute,
        second = time.second + second
    )
}

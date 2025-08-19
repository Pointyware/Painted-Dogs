package org.pointyware.painteddogs.core.ui.design

import androidx.compose.runtime.compositionLocalOf
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
interface XPDateFormatter {
    fun format(date: Instant): String
}

@OptIn(ExperimentalTime::class)
val SimpleDateFormatter = object : XPDateFormatter {
    override fun format(date: Instant): String {
        return date.toString()
    }
}
val LocalDateFormat =
    compositionLocalOf<XPDateFormatter> { throw IllegalStateException("DateFormat not provided") }

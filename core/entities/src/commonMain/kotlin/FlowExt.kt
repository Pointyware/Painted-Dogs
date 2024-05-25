package org.pointyware.painteddogs.core.entities

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

/**
 *
 */
fun <T, R> StateFlow<T>.transform(
    transform: (T) -> R,
    scope: CoroutineScope = CoroutineScope(Dispatchers.Default),
): StateFlow<R> {
    return this.map { transform(it) }.stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = transform(value)
    )
}

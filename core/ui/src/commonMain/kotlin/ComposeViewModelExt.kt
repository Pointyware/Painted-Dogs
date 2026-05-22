package org.pointyware.painteddogs.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import org.koin.mp.KoinPlatform.getKoin

inline fun <reified T: ViewModel>koinViewModel(): T {
    val koin = getKoin()
    return koin.get<T>()
}

@Composable
inline fun <reified T: ViewModel>composeKoinViewModel(): T {
    val koin = remember { getKoin() }
    return remember { koin.get<T>() }
}

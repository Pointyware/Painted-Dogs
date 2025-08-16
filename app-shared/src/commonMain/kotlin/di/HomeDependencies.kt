package org.pointyware.painteddogs.shared.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.shared.home.HomeUiStateMapper
import org.pointyware.painteddogs.shared.home.HomeViewModel

/**
 * Defines dependencies needed by all Home locations
 */
interface HomeDependencies {
    fun getHomeViewModel(): HomeViewModel
    fun getHomeUiStateMapper(): HomeUiStateMapper
}

class KoinHomeDependencies: HomeDependencies, KoinComponent {
    override fun getHomeViewModel(): HomeViewModel = get()
    override fun getHomeUiStateMapper(): HomeUiStateMapper = get()
}

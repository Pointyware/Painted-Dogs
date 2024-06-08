package org.pointyware.painteddogs.core.data.di

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.pointyware.painteddogs.core.data.FundsRepository

/**
 */
interface DataDependencies {
    fun provideFundsRepository(): FundsRepository
}

class KoinDataDependencies: DataDependencies, KoinComponent {
    override fun provideFundsRepository(): FundsRepository = get()
}

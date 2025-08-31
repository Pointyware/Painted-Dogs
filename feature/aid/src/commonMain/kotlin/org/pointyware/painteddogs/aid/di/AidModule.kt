package org.pointyware.painteddogs.aid.di

import kotlinx.coroutines.CoroutineScope
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.painteddogs.aid.data.AidRepository
import org.pointyware.painteddogs.aid.data.fake.FakeAidRepository
import org.pointyware.painteddogs.aid.viewmodels.MutualAidViewModel
import org.pointyware.painteddogs.aid.viewmodels.OfferViewModel
import org.pointyware.painteddogs.core.data.di.dataQualifier
import kotlin.coroutines.CoroutineContext

fun aidModule() = module {
    factoryOf(::MutualAidViewModel)
    factoryOf(::OfferViewModel)
//    factoryOf()

    single<AidRepository> {
        FakeAidRepository(
            get<CoroutineContext>(qualifier = dataQualifier),
            get<CoroutineScope>(qualifier = dataQualifier)
        )
    }
}

package org.pointyware.painteddogs.aid.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.pointyware.painteddogs.aid.viewmodels.MutualAidViewModel
import org.pointyware.painteddogs.aid.viewmodels.OfferScreenViewModel

fun aidModule() = module {
    factoryOf(::MutualAidViewModel)
    factoryOf(::OfferScreenViewModel)
//    factoryOf()
}

package org.pointyware.painteddogs.core.ui.di

/**
 * TODO: describe purpose/intent of Module
 */
fun coreUiModule() = org.koin.dsl.module {
    single { org.pointyware.painteddogs.core.ui.MainViewModel(get()) }
}

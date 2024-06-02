package org.pointyware.painteddogs.core.remote.di

/**
 * TODO: describe purpose/intent of Module
 */
fun coreRemoteModule() = org.koin.dsl.module {
    single { org.pointyware.painteddogs.core.remote.RemoteInteractor(get()) }
    single { org.pointyware.painteddogs.core.remote.RemoteViewModel(get()) }
}

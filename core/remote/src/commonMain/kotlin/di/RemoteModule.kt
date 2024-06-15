package org.pointyware.painteddogs.core.remote.di

import io.ktor.client.HttpClient
import org.koin.dsl.module

/**
 *
 */
fun coreRemoteModule() = module {
    single<HttpClient> {
        HttpClient() {

        }
    }
//    single { RemoteInteractor(get()) }
//    single { RemoteViewModel(get()) }
}

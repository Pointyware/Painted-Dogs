package org.pointyware.painteddogs.app.di


interface Dependencies {

}

fun MockDependencies(): Dependencies = object : Dependencies {

}

/**
 * setup koin app-level module
 */
suspend fun getDependencies(): Dependencies = PlatformDependencies()


expect class PlatformDependencies(): Dependencies {

}

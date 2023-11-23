package org.pointyware.painteddogs.feature.login

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
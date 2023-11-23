package org.pointyware.painteddogs.core.entities

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
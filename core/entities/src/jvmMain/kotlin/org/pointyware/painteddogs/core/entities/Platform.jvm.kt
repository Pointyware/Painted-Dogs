package org.pointyware.painteddogs.core.entities

class JvmPlatform : Platform {
    override val name: String = "JVM ${System.getProperty("os.name")}"
}
actual fun getPlatform(): Platform {
    return JvmPlatform()
}

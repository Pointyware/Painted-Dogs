package org.pointyware.painteddogs.api

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import org.pointyware.painteddogs.core.ads.server.ads
import org.pointyware.painteddogs.core.analytics.server.analytics

fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            ads()
            analytics()
        }
    }.start(wait = true)
}

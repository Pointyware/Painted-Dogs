package org.pointyware.painteddogs.api

import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import org.pointyware.painteddogs.api.routes.auth
import org.pointyware.painteddogs.api.routes.funds
import org.pointyware.painteddogs.api.routes.users
import org.pointyware.painteddogs.core.ads.server.ads
import org.pointyware.painteddogs.core.analytics.server.analytics

fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            auth()

            users()
            funds()

            ads()
            analytics()
        }
    }.start(wait = true)
}

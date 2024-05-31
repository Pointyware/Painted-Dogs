package org.pointyware.painteddogs.core.ads.server

import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing


fun Routing.ads() {
    get("/ads") {
        call.respondNullable<String?>(null)
        // TODO: Define analytics api routes
    }
}

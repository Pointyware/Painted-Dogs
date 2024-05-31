package org.pointyware.painteddogs.api.analytics

import io.ktor.server.application.call
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(Netty, 8080) {
        routing {
            funds()
            users()
        }
    }.start(wait = true)
}

fun Routing.funds() {
    get("/funds") {
        call.respondNullable<String?>(null)
    }
}

fun Routing.users() {
    get("/users") {
        call.respondNullable<String?>(null)
    }
}

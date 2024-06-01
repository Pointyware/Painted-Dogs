package org.pointyware.painteddogs.core.analytics.server

import io.ktor.server.application.call
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get


fun Routing.analytics() {
    get("/analytics") {
        call.respondNullable<String?>("Welcome to Analytics Endpoint")
        // TODO: Define analytics api routes
    }
}

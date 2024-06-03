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
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/ads/{id}") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/ads/campaigns") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/ads/campaigns/{campaignId}") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
}

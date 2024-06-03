package org.pointyware.painteddogs.api.routes

import io.ktor.server.application.call
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post

/**
 *
 */
fun Routing.funds() {
    get("/funds") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    post("/funds") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/funds/create") {
        call.respondNullable<String?>("Not Yet Implemented")
    }

    get("/funds/{id}") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/funds/{id}/contributions") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    post("/funds/{id}/contributions") {
        call.respondNullable<String?>("Not Yet Implemented")
    }

    get("/funds/{id}/contributions/{contributionId}") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/funds/search") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
}

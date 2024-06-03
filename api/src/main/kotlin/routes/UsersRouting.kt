package org.pointyware.painteddogs.api.routes

import io.ktor.server.application.call
import io.ktor.server.response.respondNullable
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

/**
 *
 */
fun Routing.users() {
    get("/users") {
        call.respondNullable<String?>("Not Yet Implemented")
    }

    get("/users/{id}") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/users/{id}/funds") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/users/{id}/funds") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/users/{id}/funds/{fundId}") {
        call.respondNullable<String?>("Not Yet Implemented")
    }

    get("/users/{id}/prefs") {
        call.respondNullable<String?>("Not Yet Implemented")
    }


    get("/users/{id}/contributions/{contributionId}") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
    get("/users/search") {
        call.respondNullable<String?>("Not Yet Implemented")
    }
}

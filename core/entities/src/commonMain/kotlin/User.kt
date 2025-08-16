package org.pointyware.painteddogs.core.entities

/**
 * Describes an individual human user.
 */
interface User {
    val id: Uuid
    val username: String

    val location: Location?
    val occupation: Occupation?
}

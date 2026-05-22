package org.pointyware.painteddogs.core.entities

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Describes an individual human user.
 */
@OptIn(ExperimentalUuidApi::class)
interface User {
    val id: Uuid
    val username: String

    val location: Location?
    val occupation: Occupation?
}

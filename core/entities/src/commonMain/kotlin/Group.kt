package org.pointyware.painteddogs.core.entities

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents an organizational group of users focused around some purpose.
 */
@OptIn(ExperimentalUuidApi::class)
interface Group {
    val id: Uuid
    val name: String
    val region: Region?
    val members: Set<User>
}

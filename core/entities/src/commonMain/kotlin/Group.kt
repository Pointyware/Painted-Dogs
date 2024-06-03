package org.pointyware.painteddogs.core.entities

/**
 * TODO: describe purpose/intent of Group
 */
interface Group {
    val id: Uuid
    val name: String
    val region: Region?
    val members: Set<User>
}

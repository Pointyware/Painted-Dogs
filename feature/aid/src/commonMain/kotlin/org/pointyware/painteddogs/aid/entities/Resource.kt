package org.pointyware.painteddogs.aid.entities

import kotlinx.serialization.Serializable

/**
 * Resources consist of food, housing, funds, skills, or protection.
 *
 *
 */
@Serializable
enum class Resource {
    Food,
    Housing,
    Funds,
    Skills, // TODO: consider Education?
    Protection
}

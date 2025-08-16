package org.pointyware.painteddogs.core.entities

/**
 * Allows a user to specify an account avatar without uploading a picture.
 *
 * To generate a pup:
 *   1. Associate attributes with a colors.
 *   2. Sort User Attributes
 *   3. Use different segments of sorted list for specific portions of pup texture
 */
interface Pup {
    val colorAttributes: List<Int>
}

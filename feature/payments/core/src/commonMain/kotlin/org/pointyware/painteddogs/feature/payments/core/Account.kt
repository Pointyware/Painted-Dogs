package org.pointyware.painteddogs.feature.payments.core

import org.pointyware.painteddogs.core.entities.Uuid

/**
 *  Represents a basic payment account within the :payments:core module.
 */
interface Account {
    val id: Uuid
    val accountType: AccountType
    val maskedIdentifier: String  // E.g., last 4 digits of a card, etc.
}

package org.pointyware.painteddogs.feature.payments

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 *  Represents a basic payment account within the :payments:core module.
 */
@OptIn(ExperimentalUuidApi::class)
interface Account {
    val id: Uuid
    val accountType: AccountType
    val maskedIdentifier: String  // E.g., last 4 digits of a card, etc.
}

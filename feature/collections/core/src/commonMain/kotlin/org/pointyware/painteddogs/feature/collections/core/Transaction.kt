package org.pointyware.painteddogs.feature.collections.core

import kotlinx.datetime.DateTimePeriod
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid

/**
 *  Represents a single transaction associated with a collection (e.g., a donation).
 */ 
interface Transaction {
    /**
     * Unique identifier for the transaction.
     */
    val id: Uuid

    /**
     * Context of the transaction.
     */
    val collection: Collection

    /**
     * Unique identifier for the payer.
     */
    val payerId: Uuid

    /**
     * Financial censoring for the payer.
     */
    val payerFilter: PrivacyFilter

    /**
     * Unique identifier for the receiver.
     */
    val receiverId: Uuid

    /**
     * Financial censoring for the receiver.
     */
    val receiverFilter: PrivacyFilter

    /**
     * Amount of the transaction.
     */
    val amount: CurrencyAmount

    /**
     * Time at which the transaction occurred.
     */
    val completionTime: DateTimePeriod
}

package org.pointyware.painteddogs.feature.collections.crowdfunding

import kotlinx.datetime.DateTimePeriod
import org.pointyware.painteddogs.core.entities.CurrencyAmount
import org.pointyware.painteddogs.core.entities.Uuid
import org.pointyware.painteddogs.feature.collections.core.Collection
import org.pointyware.painteddogs.feature.collections.core.CollectionType

/**
 * Represents a crowdfunding collection within the :collections:crowdfunding module. Inherits from the core Collection interface.
 */
class CrowdfundingCollection(
    override val id: Uuid,
    override val type: CollectionType = CollectionType.CROWDFUNDING,
    override val title: String,
    override val description: String,
    override val targetAmount: CurrencyAmount,
    val endDate: DateTimePeriod,
): Collection {

}

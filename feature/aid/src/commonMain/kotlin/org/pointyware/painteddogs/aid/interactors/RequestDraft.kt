package org.pointyware.painteddogs.aid.interactors

import org.pointyware.painteddogs.aid.entities.Resource
import org.pointyware.painteddogs.aid.entities.TemporalScope

/**
 * @param category Proposed [Resource] to group this resource request.
 * @param scope Proposed [TemporalScope] of the resource request.
 * @param description Proposed description of the resource request.
 */
data class RequestDraft(
    val category: Resource,
    val scope: TemporalScope,
    val description: String,
)

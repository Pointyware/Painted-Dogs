package org.pointyware.painteddogs.buildlogic.distribution

import kotlinx.coroutines.flow.Flow
import java.io.File


/**
 * Defines a transactional interface to interact with the Google Play Publishing API
 */
interface GoogleDistribution {

    /**
     * Defines attributes of the edit transaction.
     */
    interface Edit {
        /**
         * The AAB file to upload
         */
        var bundle: File?
        fun updateTracks(tracks: List<Track>)
        fun updateListing(details: ListingsDetails)
        fun executeUpdate(): Flow<Result<Progress>>
    }
    fun createEdit(): Edit

    sealed class Progress {
        data class InProgress(val progress: Float): Progress()
        data class Complete(val editId: String): Progress()
    }

    data class Track(val name: String, val userFraction: Double = 1.0, val versionCodes: List<Long>)
    data class ListingsDetails(val extra: String)
}

package org.pointyware.painteddogs.buildlogic.distribution.google

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
    fun createEdit(packageName: String): Edit

    sealed class Progress {
        data class InProgress(val progress: Float): Progress()
        data class Complete(val editId: String): Progress()
    }

    /**
     *
     */
    sealed class Track {
        /**
         * https://developers.google.com/android-publisher/tracks#how_to_compute_track_name_for_a_given_form_factor_track
         */
        abstract val name: String

        data object Internal: Track() {
            override val name: String = "qa"
        }
        data class Closed(
            override val name: String
        ): Track()
        data object Open: Track() {
            override val name: String = "beta"
        }
        data class Production(
            val userFraction: Double = 1.0,
        ): Track() {
            override val name: String = "production"
        }
    }
    data class ListingsDetails(val extra: String)
}

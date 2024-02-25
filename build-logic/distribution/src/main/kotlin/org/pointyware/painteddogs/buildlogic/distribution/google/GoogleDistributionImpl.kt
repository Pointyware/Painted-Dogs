package org.pointyware.painteddogs.buildlogic.distribution.google

import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.InputStreamContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppEdit
import com.google.api.services.androidpublisher.model.LocalizedText
import com.google.api.services.androidpublisher.model.Track
import com.google.api.services.androidpublisher.model.TrackRelease
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.pointyware.painteddogs.buildlogic.distribution.google.GoogleDistribution.Progress
import java.io.File
import java.io.IOException
import java.util.Locale

/**
 *
 */
class GoogleDistributionImpl(
    private val androidPublisher: AndroidPublisher,
//    private val account: PlayAccount
): GoogleDistribution {

    private inner class PlayEdit(
        private val packageName: String
    ): GoogleDistribution.Edit {

        override var bundle: File? = null

        private var tracks: List<GoogleDistribution.Track> = emptyList()

        override fun updateTracks(tracks: List<GoogleDistribution.Track>) {
            this.tracks = tracks
        }

        override fun updateListing(details: GoogleDistribution.ListingsDetails) {

        }

        override fun executeUpdate(): Flow<Result<Progress>> {
            return flow {
                emit(Result.success(Progress.InProgress(0.0f))) // began but nothing has been transferred

                try {
                    // Prepare Bundle
                    val bundleFile = bundle ?: throw IllegalArgumentException("No bundle set for upload")
                    emit(Result.success(Progress.InProgress(0.1f)))

                    val editsResource = androidPublisher.edits()
                    val bundlesResource = editsResource.bundles()
                    val tracksResource = editsResource.tracks()

                    val insertRequest = editsResource.insert(packageName, null)
                    val edit: AppEdit = insertRequest.execute()

                    val uploadRequest = bundlesResource.upload(
                        packageName,
                        edit.id,
                        InputStreamContent("application/octet-stream", bundleFile.inputStream())
                    )
                    val bundle = uploadRequest.execute()
                    emit(Result.success(Progress.InProgress(0.2f))) // began but nothing has been transferred
                    println("Bundle uploaded: $bundle")

                    val openTrackRequest = tracksResource.update(packageName,
                        edit.id,
                        GoogleDistribution.Track.Open.name,
                        Track().setReleases(
                            listOf(
                                TrackRelease()
                                    .setName("Open Release")
                                    .setVersionCodes(listOf(bundle.versionCode.toLong()))
                                    .setStatus("completed")
                                    .setReleaseNotes(
                                        listOf(
                                            LocalizedText()
                                                .setLanguage(Locale.US.toString())
                                                .setText("Initial release")
                                        )
                                    )
                            )
                        )
                    )
                    val track = openTrackRequest.execute()
                    /*
                    TODO: complete remaining steps to upload new build
                        1. Update tracks
                        2. Update listing
                        3. Commit
                     */

                    val commitRequest = editsResource.commit(packageName, edit.id)
                    commitRequest.execute()

                    emit(Result.success(Progress.Complete(editId = edit.id)))
                } catch (e: GoogleJsonResponseException) {
                    emit(Result.failure(e))
                } catch (e: IOException) {
                    emit(Result.failure(e))
                } catch (e: Exception) {
                    emit(Result.failure(e))
                }
            }
        }
    }

    override fun createEdit(packageName: String): GoogleDistribution.Edit {
        return PlayEdit(packageName)
    }
}

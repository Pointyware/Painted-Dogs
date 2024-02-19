package org.pointyware.painteddogs.buildlogic.distribution.google

import com.google.api.client.googleapis.json.GoogleJsonResponseException
import com.google.api.client.http.InputStreamContent
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.model.AppEdit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.pointyware.painteddogs.buildlogic.distribution.google.GoogleDistribution.Progress
import java.io.File
import java.io.IOException

/**
 *
 * [thing](https://googleapis.dev/java/google-api-services-androidpublisher/latest/com/google/api/services/androidpublisher/AndroidPublisher.html)
 *
 * https://googleapis.dev/java/google-api-services-androidpublisher/latest/com/google/api/services/androidpublisher/AndroidPublisher.Builder.html
 */
class GoogleDistributionImpl(
    private val androidPublisher: AndroidPublisher,
//    private val account: PlayAccount
): GoogleDistribution {

    private inner class PlayEdit(
        private val packageName: String
    ): GoogleDistribution.Edit {

        override var bundle: File? = null

        override fun updateTracks(tracks: List<GoogleDistribution.Track>) {

        }

        override fun updateListing(details: GoogleDistribution.ListingsDetails) {

        }

        override fun executeUpdate(): Flow<Result<Progress>> {
            return flow {
                emit(Result.success(Progress.InProgress(0.0f))) // began but nothing has been transferred

                try {
                    // Prepare Bundle
                    val bundleFile = bundle ?: throw IllegalArgumentException("No bundle set for upload")

                    val editsResource = androidPublisher.edits()
                    val bundlesResource = editsResource.bundles()

                    val insertRequest = editsResource.insert(packageName, null)
                    val edit: AppEdit = insertRequest.execute()

                    val uploadRequest = bundlesResource.upload(
                        packageName,
                        edit.id,
                        InputStreamContent("application/octet-stream", bundleFile.inputStream())
                    )
                    val bundle = uploadRequest.execute()
                    println("Bundle uploaded: $bundle")

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

    override fun createEdit(appPackage: String): GoogleDistribution.Edit {
        return PlayEdit(appPackage)
    }
}

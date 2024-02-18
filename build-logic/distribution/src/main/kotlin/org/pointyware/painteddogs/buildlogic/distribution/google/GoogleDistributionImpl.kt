package org.pointyware.painteddogs.buildlogic.distribution.google

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
    private val account: PlayAccount
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

                    val edit = AppEdit()
                    val insert = androidPublisher.edits().insert(packageName, edit)
                    insert.execute()

                    // Assuming API provides  uploading directly as bytes for simplicity
                    val bundleUpload = androidPublisher.edits().bundles().upload(
                        packageName,
                        edit.id,
                        InputStreamContent("application/octet-stream", bundleFile.inputStream())
                    )
                    bundleUpload.execute()

                    val commitEdit = androidPublisher.edits().commit(packageName, edit.id)
                    commitEdit.execute()

                    emit(Result.success(Progress.Complete(editId = edit.id)))
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

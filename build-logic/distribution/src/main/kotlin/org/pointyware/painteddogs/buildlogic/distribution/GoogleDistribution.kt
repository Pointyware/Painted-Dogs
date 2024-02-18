package org.pointyware.painteddogs.buildlogic.distribution

import java.io.File


/**
 * Defines a transactional interface to interact with the Google Play Publishing API
 *
 * https://googleapis.dev/java/google-api-services-androidpublisher/latest/com/google/api/services/androidpublisher/AndroidPublisher.html
 *
 * https://googleapis.dev/java/google-api-services-androidpublisher/latest/com/google/api/services/androidpublisher/AndroidPublisher.Builder.html
 *
 */
interface GoogleDistribution {

    /**
     * Defines attributes of the edit transaction.
     */
    interface Edit {
        /**
         * The AAB file to upload
         *
         *       # Call Edits.bundles:Upload
         *       curl -X POST \
         *         -H "X-Goog-User-Project: $PROJECT_ID" \
         *         -H "Authorization: Bearer $TOKEN" \
         *         https://androidpublisher.googleapis.com/upload/androidpublisher/v3/applications/$BUNDLE_ID/edits:insert
         */
        var bundle: File?

        /**
         *
         *       # Call Edits.tracks:Update
         *       curl -X POST \
         *         -H "X-Goog-User-Project: $PROJECT_ID" \
         *         -H "Authorization: Bearer $TOKEN" \
         *         https://androidpublisher.googleapis.com/upload/androidpublisher/v3/applications/$BUNDLE_ID/edits:insert
         */
        fun updateTracks()

        /**
         *
         *       # Call Edits.listings:Update/Patch
         *       curl -X POST \
         *         -H "X-Goog-User-Project: $PROJECT_ID" \
         *         -H "Authorization: Bearer $TOKEN" \
         *         https://androidpublisher.googleapis.com/upload/androidpublisher/v3/applications/$BUNDLE_ID/edits:insert
         */
        fun updateListing()

        /**
         * Commits the changes to the Google Play Store. The edit is no longer valid after this call.
         *
         *       # Call Edits:Commit
         *
         *       curl -X POST \
         *         -H "X-Goog-User-Project: $PROJECT_ID" \
         *         -H "Authorization: Bearer $TOKEN" \
         *         https://androidpublisher.googleapis.com/upload/androidpublisher/v3/applications/$BUNDLE_ID/edits/{editId}/bundles
         */
        fun commit()
    }
    /**
     * Begin a new edit
     *
     * https://googleapis.dev/java/google-api-services-androidpublisher/latest/com/google/api/services/androidpublisher/AndroidPublisher.Edits.Insert.html
     *
     *       # Call Edits:Insert
     *       curl -X POST \
     *         -H "X-Goog-User-Project: $PROJECT_ID" \
     *         -H "Authorization: Bearer $TOKEN" \
     *         https://androidpublisher.googleapis.com/upload/androidpublisher/v3/applications/$BUNDLE_ID/edits:insert
     */
    fun createEdit(): Edit
}

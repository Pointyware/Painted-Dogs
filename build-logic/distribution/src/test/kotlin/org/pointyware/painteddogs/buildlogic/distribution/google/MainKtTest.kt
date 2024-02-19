package org.pointyware.painteddogs.buildlogic.distribution.google

import org.junit.Test
import org.pointyware.painteddogs.buildlogic.distribution.main

/**
 *
 */
class MainKtTest {

    companion object {
        const val VALID_BUNDLE = "src/test/resources/app-android-release.aab"
        const val VALID_PACKAGE = "org.pointyware.painteddogs"
        const val VALID_ACCOUNT = "github-action-service-account@painted-dogs-prod.iam.gserviceaccount.com"
        const val VALID_ACCOUNT_FILE = "service-account.json"
        const val VALID_TRACK = "internal"

        const val INVALID_BUNDLE = "app/build/outputs/bundle/release/app-release.aab"
        const val INVALID_PACKAGE = "com.google.android.apps.maps"
        const val INVALID_ACCOUNT = "github-action-service-account@painted-dogs-prod.iam.gserviceaccount.com"
        const val INVALID_ACCOUNT_FILE = "service-account-invalid.json"
    }

    @Test
    fun valid_upload_arguments() {
        main(
            "--bundle=$VALID_BUNDLE",
            "--package=$VALID_PACKAGE",
            "--account=$VALID_ACCOUNT",
            "--key=$VALID_ACCOUNT_FILE",
            "--track=$VALID_TRACK"
        )
    }

    @Test
    fun invalid_bundle() {
        main(
            "--bundle=$INVALID_BUNDLE",
            "--package=$VALID_PACKAGE",
            "--account=$VALID_ACCOUNT",
            "--key=$VALID_ACCOUNT_FILE",
            "--track=$VALID_TRACK"
        )
    }

    @Test
    fun invalid_package() {
        main(
            "--bundle=$VALID_BUNDLE",
            "--package=$INVALID_PACKAGE",
            "--account=$VALID_ACCOUNT",
            "--key=$VALID_ACCOUNT_FILE",
            "--track=$VALID_TRACK"
        )
    }

    @Test
    fun invalid_account() {
        main(
            "--bundle=$VALID_BUNDLE",
            "--package=$VALID_PACKAGE",
            "--account=$INVALID_ACCOUNT",
            "--key=$VALID_ACCOUNT_FILE",
            "--track=$VALID_TRACK"
        )
    }

    @Test
    fun invalid_service_key() {
        main(
            "--bundle=$VALID_BUNDLE",
            "--package=$VALID_PACKAGE",
            "--account=$VALID_ACCOUNT",
            "--key=$INVALID_ACCOUNT_FILE",
            "--track=$VALID_TRACK"
        )
    }

    @Test
    fun invalid_track() {
        main(
            "--bundle=$VALID_BUNDLE",
            "--package=$VALID_PACKAGE",
            "--account=$VALID_ACCOUNT",
            "--key=$VALID_ACCOUNT_FILE",
            "--track=invalid"
        )
    }
}

package org.pointyware.painteddogs.buildlogic.distribution.google

import org.junit.Test
import org.pointyware.painteddogs.buildlogic.distribution.main

/**
 *
 */
class MainKtTest {

    @Test
    fun valid_arguments() {
        main(
            "--bundle=app/build/outputs/bundle/release/app-release.aab",
            "--package=com.example.myapp",
            "--account=github-action-service-account@painted-dogs-prod.iam.gserviceaccount.com",
            "--key=service-account.json",
            "--track=internal"
        )
    }
}

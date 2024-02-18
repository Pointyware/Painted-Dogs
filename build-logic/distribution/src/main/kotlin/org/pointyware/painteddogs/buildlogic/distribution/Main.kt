package org.pointyware.painteddogs.buildlogic.distribution

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import kotlinx.coroutines.runBlocking
import org.pointyware.painteddogs.buildlogic.distribution.google.GoogleDistribution
import org.pointyware.painteddogs.buildlogic.distribution.google.GoogleDistributionImpl
import org.pointyware.painteddogs.buildlogic.distribution.google.PlayAccount
import java.io.File
import java.io.FileInputStream
import java.util.Locale
import kotlin.system.exitProcess

enum class Token {
    HELP,
    BUNDLE,
    PACKAGE,
    ACCOUNT,
    KEY,
    TRACK
}

const val ERROR_MISSING_ARGUMENTS = 1
const val ERROR_INVALID_ARGUMENTS = 2
const val ERROR_UPLOAD_FAILED = 3


private fun printHelp() {
    println("Usage: upload_bundle --bundle=<bundle-path> --package=<package-name> --account=<service_account_email> --key=<service_account_key_file> --track=<track>")
}

/**
 *
 */
fun main(vararg args: String) {
    println("Working directory: ${System.getProperty("user.dir")}")

    var appBundlePath: String? = null
    var packageName: String? = null
    var serviceAccountEmail: String? = null
    var serviceAccountKeyFile: String? = null
    var track: String? = null

    val argIterator = args.iterator()
    while (argIterator.hasNext()) {
        val token = argIterator.next()
        if (token.startsWith("--")) {
            val valueStart = token.indexOf('=')
            val tokenName: String = if (valueStart == -1) token.substring(2) else token.substring(2, valueStart)
            val tokenValue: String? = if (valueStart == -1) null else token.substring(valueStart + 1)
            when (Token.valueOf(tokenName.uppercase(Locale.getDefault()))) {
                Token.HELP -> {
                    printHelp()
                    return
                }
                Token.BUNDLE -> {
                    appBundlePath = tokenValue
                }
                Token.PACKAGE -> {
                    packageName = tokenValue
                }
                Token.ACCOUNT -> {
                    serviceAccountEmail = tokenValue
                }
                Token.KEY -> {
                    serviceAccountKeyFile = tokenValue
                }
                Token.TRACK -> {
                    track = tokenValue
                }
            }
        } else {
            println("Unknown token: $token")
        }
    }
    if (appBundlePath == null
        || packageName == null
        || serviceAccountEmail == null
        || serviceAccountKeyFile == null
        || track == null
        ) {
        printHelp()
        exitProcess(ERROR_MISSING_ARGUMENTS)
    }

    val credentials = ServiceAccountCredentials.fromStream(FileInputStream(serviceAccountKeyFile))
        .createScoped("https://www.googleapis.com/auth/androidpublisher")
    val httpCredentialsAdapter = HttpCredentialsAdapter(credentials)

    val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    val jsonFactory = GsonFactory.getDefaultInstance()
    val publisher = AndroidPublisher.Builder(httpTransport, jsonFactory, httpCredentialsAdapter)
        .setApplicationName("PaintedDogs Desktop")
        .build()
    val account = PlayAccount(serviceAccountEmail, serviceAccountKeyFile)
    val dist: GoogleDistribution = GoogleDistributionImpl(publisher, account)

    val edit = dist.createEdit("org.pointyware.painteddogs")
    edit.bundle = File("app-release.aab")
    edit.updateTracks(emptyList()) // set track
    edit.updateListing(GoogleDistribution.ListingsDetails("nothing")) // set listing details
    val updateFlow = edit.executeUpdate()
    runBlocking {
        updateFlow.collect {
            it.getOrNull()?.let { progress ->
                when (progress) {
                    is GoogleDistribution.Progress.InProgress -> {
                        println("Progress: ${progress.progress}")
                    }
                    is GoogleDistribution.Progress.Complete -> {
                        println("Upload complete: ${progress.editId}")
                    }
                }
            } ?: run {
                println("Upload failed")
                it.exceptionOrNull()?.printStackTrace()
                exitProcess(ERROR_UPLOAD_FAILED)
            }
        }
    }
}

package org.pointyware.painteddogs.buildlogic.distribution

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.auth.http.HttpCredentialsAdapter
import com.google.auth.oauth2.ServiceAccountCredentials
import org.pointyware.painteddogs.buildlogic.distribution.google.PlayAccount
import java.io.File
import java.io.FileInputStream
import java.util.Locale

enum class Token {
    HELP,
    BUNDLE,
    PACKAGE,
    ACCOUNT,
    KEY,
    TRACK
}

private fun printHelp() {
    println("Usage: upload_bundle --bundle=<bundle-path> --package=<package-name> --account=<service_account_email> --key=<service_account_key_file> --track=<track>")
}

/**
 *
 */
fun main(vararg args: String) {

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
        return
    }
// Assuming the JSON key is at some secure file path within your project's context
    val serviceAccountKeyPath = "service-account.json"
    val credentials = ServiceAccountCredentials.fromStream(FileInputStream(serviceAccountKeyPath))
        .createScoped("https://www.googleapis.com/auth/androidpublisher")
    val httpCredentialsAdapter = HttpCredentialsAdapter(credentials)

    val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    val jsonFactory = GsonFactory.getDefaultInstance()
    val publisher = AndroidPublisher.Builder(httpTransport, jsonFactory, httpCredentialsAdapter)
        .setApplicationName("PaintedDogs Desktop")
        .build()
    val account = PlayAccount(serviceAccountEmail, serviceAccountKeyFile)
    val dist: GoogleDistribution = GoogleDistributionImpl(publisher, account)

    val edit = dist.createEdit()
    edit.bundle = File("app-release.aab")
//    edit.updateTracks() // set track
//    edit.updateListing() // set listing details
//    edit.executeUpdate()
}

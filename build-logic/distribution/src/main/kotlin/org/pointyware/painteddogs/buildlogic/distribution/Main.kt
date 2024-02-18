package org.pointyware.painteddogs.buildlogic.distribution

import java.io.File
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
    val dist: GoogleDistribution = object: GoogleDistribution{
        override fun createEdit(): GoogleDistribution.Edit {
            return object: GoogleDistribution.Edit {
                override var bundle: File? = null
                    get() {
                        println("getBundle()")
                        return field
                    }
                    set(value) {
                        println("setBundle($value)")
                        field = value
                    }

                override fun updateTracks() {
                    println("updateTracks()")
                }

                override fun updateListing() {
                    println("updateListing()")
                }

                override fun commit() {
                    println("commit()")
                }
            }
        }
    }

    val edit = dist.createEdit()
    edit.bundle = File("app-release.aab")
    edit.updateTracks()
    edit.updateListing()
    edit.commit()
}

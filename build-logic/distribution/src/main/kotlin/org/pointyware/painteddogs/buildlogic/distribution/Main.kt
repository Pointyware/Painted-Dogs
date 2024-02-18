package org.pointyware.painteddogs.buildlogic.distribution

import java.io.File

/**
 *
 */
fun main() {
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

package org.pointyware.painteddogs.buildlogic.conventions

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Applies the Kotlin Multiplatform plugin to the project and sets up an intermediate java target
 * to be shared by android and jvm targets.
 */
class KmmConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            println("Applying KmmConventionPlugin to $name")
        }

        target.pluginManager.apply("org.jetbrains.kotlin.multiplatform")
        /*
        TODO: setup intermediate
          1. reapply default target hierarchy
          2. create intermediate source set
          3. create dependsOn in android/jvm targets
         */
    }
}

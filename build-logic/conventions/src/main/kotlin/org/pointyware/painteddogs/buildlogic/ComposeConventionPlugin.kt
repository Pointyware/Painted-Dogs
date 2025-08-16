package org.pointyware.painteddogs.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Includes the core projects in the dependencies of the project after applying the kmp-convention.
 */
class ComposeConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("org.jetbrains.compose")
        target.plugins.apply("org.jetbrains.kotlin.plugin.compose")

        // Add dependencies after evaluation
        target.afterEvaluate {
            dependencies {
                /*

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                 */
            }
        }
    }
}

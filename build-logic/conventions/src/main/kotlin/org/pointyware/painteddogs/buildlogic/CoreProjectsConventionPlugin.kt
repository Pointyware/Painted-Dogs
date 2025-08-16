package org.pointyware.painteddogs.buildlogic

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Includes the core projects in the dependencies of the project after applying the kmp-convention.
 */
class CoreProjectsConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.plugins.apply("org.pointyware.painteddogs.kmp-convention")

        // Add dependencies after evaluation
        target.afterEvaluate {
            dependencies {
                listOf(
                    "common",
                    "ads:client",
                    "analytics",
                    "data",
                    "entities",
                    "interactors",
                    "local",
                    "navigation",
                    "remote",
                    "ui",
                    "view-models"
                ).forEach {
                    add("implementation", project(":core:$it"))
                }
            }
        }
    }
}

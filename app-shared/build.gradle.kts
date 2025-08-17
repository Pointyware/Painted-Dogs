/*
 * Copyright (c) 2025 Pointyware. Use of this software is governed by the Apache 2.0 license. See project root for full text.
 */

import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeHelper)
    alias(libs.plugins.kotlinxKover)
    alias(libs.plugins.serialization)
}

kotlin {

    jvmToolchain(21)
    jvm("desktop")
    androidTarget() {

    }
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
//                api(projects)

                implementation(projects.core.common)
                implementation(projects.core.entities)
                implementation(projects.core.local)
                implementation(projects.core.remote)
                implementation(projects.core.data)
                implementation(projects.core.interactors)
                implementation(projects.core.viewModels)
                implementation(projects.core.navigation)
                implementation(projects.core.ui)

                implementation(projects.feature.profiles)
                implementation(projects.feature.funds)
                implementation(projects.feature.payments)
                implementation(projects.feature.login)
                implementation(projects.feature.chat)
                implementation(projects.feature.events)
                implementation(projects.feature.groups)

                // UI
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.material3AdaptiveNavigationSuite)
                implementation(compose.components.resources)
                implementation(libs.compose.navigation)
                implementation(libs.compose.backhandler)

                // DI
                implementation(libs.koin.core)
                implementation(libs.koin.coroutines)

                // Network
                implementation(libs.ktor.clientCore)

                // KotlinX
                implementation(libs.kotlinx.coroutines)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
                implementation(libs.kotlinx.coroutinesTest)

                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)

                implementation(libs.koin.test)
                implementation(libs.pointyware.kass)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.uiToolingPreview)

                implementation(libs.kotlinx.coroutinesAndroid)
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)

                implementation(libs.kotlinx.coroutinesSwing)
            }
        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.shared"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    debugImplementation(libs.androidx.uiTooling)
}

compose.resources {
    generateResClass = always
    publicResClass = true
    packageOfResClass = "org.pointyware.painteddogs.shared"
    generateResClass = always
}

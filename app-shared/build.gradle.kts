import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.painteddogs.koin)
    alias(libs.plugins.painteddogs.kmp)
    alias(libs.plugins.painteddogs.coreProjects)
    alias(libs.plugins.painteddogs.compose)
}

kotlin {
    jvm {

    }
    androidTarget {

    }
    val framework = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "app_shared"
            isStatic = true
            framework.add(this)
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.feature.chat)
                implementation(projects.feature.funds)
                implementation(projects.feature.events)
                implementation(projects.feature.groups)
                implementation(projects.feature.login)
                implementation(projects.feature.payments)
                implementation(projects.feature.profiles)

                implementation(libs.kotlinx.dateTime)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)

                implementation(libs.pointyware.kass)
            }
        }

        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(libs.kotlinx.coroutinesSwing)
            }
        }
    }
}
compose.resources {
    publicResClass = true
    packageOfResClass = "org.pointyware.painteddogs.shared"
    generateResClass = always
}

android {
    namespace = "org.pointyware.painteddogs.shared"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}

import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.painteddogs.koin)
    alias(libs.plugins.painteddogs.kmp)
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
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
                implementation(project(":core:ads"))
                implementation(project(":core:analytics"))
                implementation(project(":core:data"))
                implementation(project(":core:entities"))
                implementation(project(":core:interactors"))
                implementation(project(":core:local"))
                implementation(project(":core:navigation"))
                implementation(project(":core:remote"))
                implementation(project(":core:ui"))
                implementation(project(":core:view-models"))

                implementation(project(":feature:chat"))
                implementation(project(":feature:collections"))
                implementation(project(":feature:events"))
                implementation(project(":feature:groups"))
                implementation(project(":feature:login"))
                implementation(project(":feature:payments"))
                implementation(project(":feature:profiles"))

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.components.uiToolingPreview) // fleet support

                implementation(libs.kotlinx.dateTime)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)

                implementation(project(":assertions"))
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

android {
    namespace = "org.pointyware.painteddogs.app"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}

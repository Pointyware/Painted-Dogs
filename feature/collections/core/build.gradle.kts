import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
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
            baseName = "feature_collections_core"
            isStatic = true
            framework.add(this)
        }
    }

    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:entities"))
                implementation(project(":core:interactors"))
                implementation(project(":core:data"))
                implementation(project(":core:local"))
                implementation(project(":core:remote"))
                implementation(project(":core:view-models"))
                implementation(project(":core:ui"))

                implementation(libs.kotlinx.dateTime)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.koin.core)

                implementation(compose.runtime)
                implementation(compose.preview)
                implementation(compose.material3)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":assertions"))

                implementation(libs.kotlin.test)
                implementation(libs.koin.test)
                implementation(libs.kotlinx.coroutinesTest)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
            }
        }

        val jvmSharedMain by creating {
            dependsOn(commonMain)
        }
        val jvmSharedTest by creating {
            dependsOn(commonTest)
        }

        val jvmMain by getting {
            dependsOn(jvmSharedMain)
        }
        val jvmTest by getting {
            dependsOn(jvmSharedTest)
            dependencies {
                implementation(libs.truth)
                implementation(libs.mockk)
                implementation(libs.jupiter)
            }
        }

        val androidMain by getting {
            dependsOn(jvmSharedMain)
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.koin.android)
            }
        }
        val androidUnitTest by getting {
            dependsOn(jvmSharedTest)
        }

        val iosMain by getting {
            dependencies {
            }
        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.feature.collections.core"
    compileSdk = 34
    defaultConfig {
        minSdk = 21
    }
}

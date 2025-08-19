plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeHelper)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.serialization)
}

kotlin {
    jvm {
    }
    androidTarget {
    }
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()


    applyDefaultHierarchyTemplate()
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":core:ui"))

                implementation(libs.kotlinx.coroutines)

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(libs.compose.navigation)
                implementation(compose.components.resources)

                implementation(libs.koin.core)

                implementation(libs.kotlinx.serialization.json)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
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
            }
        }

        val androidMain by getting {
            dependsOn(jvmSharedMain)
        }
        val androidUnitTest by getting {
            dependsOn(jvmSharedTest)
        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.core.navigation"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
}

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
                implementation(projects.core.common)
                implementation(projects.core.entities)
                implementation(projects.core.navigation)
                implementation(projects.core.ui)

                implementation(projects.feature.funds)

                implementation(libs.koin.core)
                implementation(libs.kotlinx.serialization.json)

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(libs.compose.navigation)
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
    namespace = "org.pointyware.painteddogs.feature.profiles"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
}

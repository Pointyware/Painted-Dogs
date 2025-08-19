plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeHelper)
    alias(libs.plugins.composeMultiplatform)
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
                implementation(projects.core.viewModels)
                implementation(projects.core.ui)

                implementation(compose.ui)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.components.uiToolingPreview) // fleet support

                implementation(libs.kotlinx.dateTime)
                implementation(libs.kotlinx.coroutines)
                implementation(libs.koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.pointyware.kass)

                implementation(libs.kotlin.test)
                implementation(libs.koin.test)
                implementation(libs.kotlinx.coroutinesTest)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.uiTest)
                implementation(libs.mockative)
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
            dependencies {
                implementation(libs.androidx.uiTooling)
                implementation(libs.androidx.uiToolingPreview)
            }
        }
        val androidUnitTest by getting {
            dependsOn(jvmSharedTest)
        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.feature.chat"
    compileSdk = 36
    defaultConfig {
        minSdk = 21
    }
}

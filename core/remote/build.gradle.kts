plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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

                implementation(libs.koin.core)

                api(libs.ktor.clientCore)
                api(libs.ktor.clientContentNegotiation)
                api(libs.ktor.serializationKotlinxJson)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        val jvmSharedMain by creating {
            dependsOn(commonMain)

            dependencies {
                implementation(libs.ktor.clientOkhttp)
            }
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

//        val nativeMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.cio)
//            }
//        }
//        val nativeTest by getting {
//
//        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.core.remote"
    compileSdk = 36
    defaultConfig {
        minSdk = 24
    }
}

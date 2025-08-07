plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeHelper)
    alias(libs.plugins.composeMultiplatform)
}

kotlin {
    androidTarget {
    }
//    iosX64()
//    iosArm64()
//    iosSimulatorArm64()


    sourceSets {
        commonMain.dependencies {
            implementation(projects.feature.profiles)

            implementation(libs.koin.core)

            implementation(compose.ui)
            implementation(compose.material3)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.pointyware.painteddogs.feature.login"
    compileSdk = 36
    defaultConfig {
        minSdk = 21
    }
}
